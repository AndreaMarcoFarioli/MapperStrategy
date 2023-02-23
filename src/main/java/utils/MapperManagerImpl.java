package utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Map;
import java.util.Objects;


public class MapperManagerImpl implements MapperManager{

	private final Map<CastSignatureKey, GenericMapping<?, ?>> map = new Hashtable<>();
	private static MapperManagerImpl instance = null;

	private MapperManagerImpl(){}

	public static MapperManagerImpl getInstance() {
		if (Objects.isNull(instance)){
			instance = new MapperManagerImpl();
		}
		return instance;
	}

	@Override
	public <T> T convert(Object source, Class<T> target) {
		Method lambdaCast = getMapperMethod(source.getClass(), target);
		if (Objects.isNull(lambdaCast)){
			throw new RuntimeException();
		}

		Object obj = map.get(matchesKeys(source.getClass(), target));

		try {

			Object res = lambdaCast.invoke(obj,source);
			return target.cast(res);

		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new RuntimeException(e);
		}
	}

	private Method getMapperMethod(Class<?> source, Class<?> target){
		var key = matchesKeys(source, target);

		if (Objects.isNull(key)){
			throw new RuntimeException();
		}

		GenericMapping<?,?> mapper = map.get(key);

		return Arrays.stream(mapper.getClass().getMethods())
				.filter(method ->
					method.getReturnType().equals(target) && method.getParameterTypes()[0].equals(source))
				.findFirst().orElse(null);

	}

	private CastSignatureKey matchesKeys(Class<?> source, Class<?> target){
		CastSignatureKey keyCfr = new CastSignatureKey(source, target);

		return map.keySet()
				.stream()
				.filter(keyCfr::equals)
				.findFirst().orElse(null);
	}

	@Override
	public <A, B> void registerMapper(GenericMapping<A, B> mapping, Class<A> a, Class<B> b) {
		if (Objects.isNull(mapping)){
			throw new RuntimeException();
		}

		CastSignatureKey castSignatureKey = new CastSignatureKey(a,b);
		map.put(castSignatureKey, mapping);
	}

}

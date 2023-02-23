package utils;

import exceptions.CastMethodNotFoundException;
import exceptions.CasterNotInvokableException;

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
		CastSignatureKey key = new CastSignatureKey(source.getClass(), target);
		Method lambdaCast = getMapperMethod(key);

		if (Objects.isNull(lambdaCast)){
			throw new CastMethodNotFoundException();
		}

		Object obj = map.get(key);

		try {

			Object res = lambdaCast.invoke(obj,source);
			return target.cast(res);

		} catch (IllegalAccessException e) {
			throw new CastMethodNotFoundException();
		} catch (InvocationTargetException e) {
			throw new CasterNotInvokableException();
		}
	}

	private Method getMapperMethod(CastSignatureKey key){
		GenericMapping<?,?> mapper = map.get(key);

		return Arrays.stream(mapper.getClass().getMethods())
				.filter(method ->
					method.getReturnType().equals(key.getSecond()) && method.getParameterTypes()[0].equals(key.getFirst()))
				.findFirst().orElse(null);

	}

	@Override
	public <A, B> void registerMapper(GenericMapping<A, B> mapping, Class<A> a, Class<B> b) {
		if (Objects.isNull(mapping)){
			throw new NullPointerException();
		}

		CastSignatureKey castSignatureKey = new CastSignatureKey(a,b);
		map.put(castSignatureKey, mapping);
	}

}

package utils;

import java.util.Objects;

public interface MapperManager {
	<T> T convert(Object source, Class<T> targetClass);

	//Generic mapper already cast from a to b and from b to a
	<A,B> void registerMapper(GenericMapping<A,B> mapping, Class<A> a, Class<B> b);
}

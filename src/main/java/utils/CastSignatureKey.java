package utils;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class CastSignatureKey {
	private final Class<?> first;
	private final Class<?> second;

	private final Set<Class<?>> unorderedCasting;

 	public CastSignatureKey(Class<?> first, Class<?> second){
		unorderedCasting = new HashSet<>();
		this.first = first;
		this.second = second;
		unorderedCasting.add(first);
		unorderedCasting.add(second);

	}

	@Override
	public boolean equals(Object obj) {
		if (Objects.isNull(obj) || !(obj instanceof CastSignatureKey signatureKey)) {
			return false;
		}
		return signatureKey.first.equals(first) && signatureKey.second.equals(second)
				|| signatureKey.first.equals(second) && signatureKey.second.equals(first);
	}

	@Override
	public int hashCode() {
		return unorderedCasting.hashCode();
	}

	public Class<?> getFirst() {
		return first;
	}

	public Class<?> getSecond() {
		return second;
	}
}

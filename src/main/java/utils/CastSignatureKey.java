package utils;

import java.util.Objects;

public class CastSignatureKey {

	private final Class<?> first, second;

	public CastSignatureKey(Class<?> first, Class<?> second) {
		this.first = first;
		this.second = second;
	}


	@Override
	public boolean equals(Object obj) {
		if (Objects.isNull(obj) || !(obj instanceof CastSignatureKey signatureKey)){
			return false;
		}
		return signatureKey.first.equals(first) && signatureKey.second.equals(second)
				|| signatureKey.first.equals(second) && signatureKey.second.equals(first);
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	public Class<?> getFirst() {
		return first;
	}

	public Class<?> getSecond() {
		return second;
	}
}

package utils;

import java.util.Objects;

public record CastSignatureKey(Class<?> first, Class<?> second) {
	@Override
	public boolean equals(Object obj) {
		if (Objects.isNull(obj) || !(obj instanceof CastSignatureKey signatureKey)) {
			return false;
		}
		return signatureKey.first.equals(first) && signatureKey.second.equals(second)
				|| signatureKey.first.equals(second) && signatureKey.second.equals(first);
	}


}

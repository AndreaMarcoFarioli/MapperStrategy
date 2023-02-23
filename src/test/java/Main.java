import utils.CastSignatureKey;
import utils.GenericMapping;
import utils.MapperManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {
	public static void main(String[] args) throws ClassNotFoundException, IOException {
		CastSignatureKey castSignatureKey = new CastSignatureKey(GenericMapping.class, MapperManager.class);
		System.out.println(castSignatureKey.hashCode());

	}
}

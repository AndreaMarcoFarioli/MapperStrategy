import entities.EntityA;
import entities.EntityB;
import mappers.Mapper;
import mappers.MapperAB;
import utils.CastSignatureKey;
import utils.MapperManager;
import utils.MapperManagerImpl;

public class Main {
	public static void main(String[] args) throws ClassNotFoundException {
		MapperManager mapperManager = MapperManagerImpl.getInstance();

		EntityA a = new EntityA();
		a.setName("Andrea");
		a.setSurname("Farioli");

		mapperManager.registerMapper(Mapper.getInstance(), EntityA.class, EntityB.class);

		System.out.println(a);


		EntityB entityB = mapperManager.convert(a, EntityB.class);
		a = mapperManager.convert(entityB, EntityA.class);

		System.out.println(entityB);
		System.out.println(a);

	}
}

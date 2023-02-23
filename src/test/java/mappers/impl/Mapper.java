package mappers.impl;

import entities.EntityA;
import entities.EntityB;
import mappers.MapperAB;

import java.util.Objects;

public class Mapper implements MapperAB {

	private static Mapper instance = null;

	private Mapper(){}

	public static Mapper getInstance() {
		if (Objects.isNull(instance)){
			instance = new Mapper();
		}
		return instance;
	}

	@Override
	public EntityB sourceToT(EntityA source) {
		EntityB entityB = new EntityB();
		entityB.setSurname(source.getSurname());
		return entityB;
	}

	@Override
	public EntityA sourceToS(EntityB source) {
		EntityA entityA = new EntityA();
		entityA.setSurname(source.getSurname());
		return entityA;
	}
}

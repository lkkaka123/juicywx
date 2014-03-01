package com.juicywx.store;

import com.juicywx.data.Boss;

import com.google.appengine.api.datastore.Entity;

public class BossStore {
	public Entity getEntity(Boss boss){
		Entity entity=new Entity("juicywx",Boss.tag);
		entity.setProperty("Id",boss.Id);
		entity.setProperty("marketName",boss.marketName);
		return entity;
	}
	public Boss readFrom(Entity entity){
		Boss boss = new Boss();
		boss.marketName=(String) entity.getProperty("marketName");
		boss.Id=(String) entity.getProperty("Id");
		return boss;
	}
}

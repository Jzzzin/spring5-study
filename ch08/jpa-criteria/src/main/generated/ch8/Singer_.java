package ch8;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor", date = "2020-02-20T19:27:36.644+0900")
@StaticMetamodel(Singer.class)
public abstract class Singer_ {

	public static volatile SingularAttribute<Singer, String> firstName;
	public static volatile SingularAttribute<Singer, String> lastName;
	public static volatile SetAttribute<Singer, Album> albums;
	public static volatile SetAttribute<Singer, Instrument> instruments;
	public static volatile SingularAttribute<Singer, Long> id;
	public static volatile SingularAttribute<Singer, Integer> version;
	public static volatile SingularAttribute<Singer, Date> birthDate;

	public static final String FIRST_NAME = "firstName";
	public static final String LAST_NAME = "lastName";
	public static final String ALBUMS = "albums";
	public static final String INSTRUMENTS = "instruments";
	public static final String ID = "id";
	public static final String VERSION = "version";
	public static final String BIRTH_DATE = "birthDate";

}


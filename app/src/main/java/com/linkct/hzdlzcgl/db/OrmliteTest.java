package com.linkct.hzdlzcgl.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.linkct.hzdlzcgl.domain.CzpInfo;
import com.linkct.hzdlzcgl.domain.DataInfo;
import com.linkct.hzdlzcgl.domain.DzdInfo;
import com.linkct.hzdlzcgl.domain.ImageInfo;
import com.linkct.hzdlzcgl.domain.WxjlInfo;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class OrmliteTest extends OrmLiteSqliteOpenHelper {

	private static final String TABLE_NAME = "sqlite-test.db";
	private static OrmliteTest instance;
	private Map<String, Dao> daos = new HashMap<String, Dao>();  

	private OrmliteTest(Context context) {
		super(context, TABLE_NAME, null, 4);
	}

	@Override
	public void onCreate(SQLiteDatabase arg0, ConnectionSource arg1) {
		try {
			TableUtils.createTable(connectionSource, DataInfo.class);
			TableUtils.createTable(connectionSource, WxjlInfo.class);
			TableUtils.createTable(connectionSource, CzpInfo.class);
			TableUtils.createTable(connectionSource, DzdInfo.class);
			TableUtils.createTable(connectionSource, ImageInfo.class);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void onUpgrade(SQLiteDatabase database, ConnectionSource arg1, int arg2,
			int arg3) {
	}
    public static synchronized OrmliteTest getHelper(Context context)  
    {  
//        context = context.getApplicationContext();  
        if (instance == null)  
        {  
            synchronized (OrmliteTest.class)  
            {  
                if (instance == null)  
                    instance = new OrmliteTest(context);  
            }  
        }  
  
        return instance;  
    }  
    
    public synchronized Dao getDao(Class clazz) throws SQLException  
    {  
        Dao dao = null;  
        String className = clazz.getSimpleName();  
  
        if (daos.containsKey(className))  
        {  
            dao = daos.get(className);  
        }  
        if (dao == null)  
        {  
            dao = super.getDao(clazz);  
            daos.put(className, dao);  
        }  
        return dao;  
    }  
    
    /** 
     */
    @Override  
    public void close()  
    {  
        super.close();  
  
        for (String key : daos.keySet())  
        {  
            Dao dao = daos.get(key);  
            dao = null;  
        }  
    }  
}

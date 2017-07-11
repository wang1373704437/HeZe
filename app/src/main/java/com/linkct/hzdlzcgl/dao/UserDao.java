package com.linkct.hzdlzcgl.dao;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.misc.TransactionManager;
import com.linkct.hzdlzcgl.db.OrmliteTest;
import com.linkct.hzdlzcgl.domain.CzpInfo;
import com.linkct.hzdlzcgl.domain.DataInfo;
import com.linkct.hzdlzcgl.domain.DzdInfo;
import com.linkct.hzdlzcgl.domain.ImageInfo;
import com.linkct.hzdlzcgl.domain.WxjlInfo;

import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.Callable;

public class UserDao {
    private Context context;
    private Dao<DataInfo, Integer> dataInfoDaoOpe;
    private Dao<WxjlInfo, Integer> wxDaoOpe;//维修记录
    private Dao<CzpInfo, Integer> czPDaoOpe;//操作记录
    private Dao<DzdInfo, Integer> dzdDaoOpe;//定值单
    private Dao<ImageInfo, Integer> imageDaoOpe;//图片数据库
    private OrmliteTest helper;

    public UserDao() {

    }

    public UserDao(Context context) {
        this.context = context;
        try {
            helper = OrmliteTest.getHelper(context);
            dataInfoDaoOpe = helper.getDao(DataInfo.class);
            wxDaoOpe = helper.getDao(WxjlInfo.class);
            czPDaoOpe = helper.getDao(CzpInfo.class);
            dzdDaoOpe = helper.getDao(DzdInfo.class);
            imageDaoOpe = helper.getDao(ImageInfo.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void cleanALL() {
        try {
            dataInfoDaoOpe.queryRaw("delete from tb_data");
            wxDaoOpe.queryRaw("delete from tb_Wxjl");
            czPDaoOpe.queryRaw("delete from tb_czp");
            dzdDaoOpe.queryRaw("delete from tb_dzd");
            imageDaoOpe.queryRaw("delete from tb_image");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param dataInfo
     */
    public void add(DataInfo dataInfo) {
        try {
            dataInfoDaoOpe.createOrUpdate(dataInfo);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addDataList(final List<DataInfo> list) {
        try {
            TransactionManager.callInTransaction(helper.getConnectionSource(),
                    new Callable<Void>() {
                        @Override
                        public Void call() throws Exception {
                            for (DataInfo wx : list) {
                                dataInfoDaoOpe.createOrUpdate(wx);
                            }
                            return null;
                        }
                    });
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /**
     * @param uuid
     * @return
     */
    public List<DataInfo> listByDataInfoIdByUUid(String uuid) {
        try {
            return dataInfoDaoOpe.queryBuilder().orderBy("opttime", true).where().eq("uuid", uuid)
                    .query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @param pid
     * @return
     */
    public List<DataInfo> listByDataInfoIdbyPid(String pid) {
        try {
            return dataInfoDaoOpe.queryBuilder().orderBy("opttime", true).where().eq("pid", pid)
                    .query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @param pid
     * @return
     */
    public List<DataInfo> findByDataInfoIdbyPid(String pid) {
        try {
            return dataInfoDaoOpe.queryBuilder().orderBy("opttime", true).where().eq("pid", pid)
                    .query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * �����
     *
     * @param dataInfo
     * @return
     */
    public void updateDataInfo(DataInfo dataInfo) {
        try {
            dataInfoDaoOpe.createOrUpdate(dataInfo);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public DataInfo getDataById(int id) {
        DataInfo article = null;
        try {
            article = dataInfoDaoOpe.queryForId(id);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return article;
    }

    public List<WxjlInfo> listWxJlByUuid(String uuid) {
        try {
            return wxDaoOpe.queryBuilder().orderBy("wxTime", false).where().eq("uuid", uuid)
                    .query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void addWxjl(final List<WxjlInfo> list) {
        try {
            TransactionManager.callInTransaction(helper.getConnectionSource(),
                    new Callable<Void>() {
                        @Override
                        public Void call() throws Exception {
                            for (WxjlInfo wx : list) {
                                wxDaoOpe.createOrUpdate(wx);
                            }
                            return null;
                        }
                    });
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addImageList(final List<ImageInfo> list) {
        try {
            TransactionManager.callInTransaction(helper.getConnectionSource(),
                    new Callable<Void>() {
                        @Override
                        public Void call() throws Exception {
                            for (ImageInfo wx : list) {
                                imageDaoOpe.createOrUpdate(wx);
                            }
                            return null;
                        }
                    });
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<ImageInfo> listImagesByUuid(String uuid) {
        try {
            return imageDaoOpe.queryBuilder().orderBy("_id", true).where().eq("uuid", uuid)
                    .query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * false 降序
     * @param uuid
     * @return
     */
    public List<CzpInfo> listCzpByUuid(String uuid) {

        try {
            return czPDaoOpe.queryBuilder().orderBy("czpTime", false).where().eq("uuid", uuid)
                    .query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void addczpList(final List<CzpInfo> czList) {

        try {
            TransactionManager.callInTransaction(helper.getConnectionSource(),
                    new Callable<Void>() {
                        @Override
                        public Void call() throws Exception {
                            for (CzpInfo cz : czList) {
                                czPDaoOpe.createOrUpdate(cz);
                            }
                            return null;
                        }
                    });
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<DzdInfo> listDzdByUuid(String uuid) {
        try {
            return dzdDaoOpe.queryBuilder().orderBy("dzdTime", false).where().eq("uuid", uuid)
                    .query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void addDzdList(final List<DzdInfo> dzdList) {
        try {
            TransactionManager.callInTransaction(helper.getConnectionSource(),
                    new Callable<Void>() {
                        @Override
                        public Void call() throws Exception {
                            for (DzdInfo dzd : dzdList) {
                                dzdDaoOpe.createOrUpdate(dzd);
                            }
                            return null;
                        }
                    });
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

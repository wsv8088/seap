package com.wsun.seap.dao.common;

import com.wsun.seap.common.exception.db.DbException;
import com.wsun.seap.dao.context.Page;
import com.wsun.seap.dao.context.QueryParam;
import com.wsun.seap.dao.interceptor.SQLHelper;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.SqlSessionUtils;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Administrator on 2014/10/5 0005.
 */
public abstract class MyBatisBaseDao extends SqlSessionDaoSupport {

	private static Logger logger = LoggerFactory.getLogger(MyBatisBaseDao.class);


	@Autowired()
	public final void setSqlSessionFactory (SqlSessionFactory sqlSessionFactory) {
		super.setSqlSessionFactory(sqlSessionFactory);
	}

	private final static String DELIMITER = ".";

	protected abstract String getNamespace ();

	public <T> boolean insert (String classMethod, Object param) {
		boolean flag = false;
		try {
			flag = this.getSqlSession().insert(getNamespace() + DELIMITER + classMethod, param) > 0 ? true : false;
		} catch (Exception e) {
			flag = false;
			logger.error(e.getMessage());
		}
		return flag;
	}

	public <T> boolean update (String classMethod, Object param) {
		boolean flag = false;
		try {
			flag = this.getSqlSession().update(getNamespace() + DELIMITER + classMethod, param) > 0 ? true : false;
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return flag;
	}

	public <T> boolean delete (String classMethod, Object param) {
		boolean flag = false;
		try {
			flag = this.getSqlSession().delete(getNamespace() + DELIMITER + classMethod, param) > 0 ? true : false;
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return flag;
	}

	public <T> T queryForObject (String classMethod, Object param) {
		T result = null;
		try {
			result = (T) this.getSqlSession().selectOne(getNamespace() + DELIMITER + classMethod, param);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return result;
	}

	public <T> List<T> queryAll (String classMethod, Object param) {
		List<T> list = null;
		try {
			list = this.getSqlSession().selectList(getNamespace() + DELIMITER + classMethod, param);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return list;
	}

	public <T> Page<T> queryForPage (String classMethod, QueryParam queryParam) {
		Page<T> page = new Page<T>();
		try {
			List<T> list = this.getSqlSession().selectList(getNamespace() + DELIMITER + classMethod, queryParam);
			page.setRows(list);
			int total = selectCount(classMethod, queryParam);
			page.setTotal(total);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return page;
	}

	/**
	 * 查询个数
	 * @param statement
	 * @param parameter
	 * @return
	 */
	protected int selectCount (String statement, Object parameter) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		SqlSession newSession = null;
		try {
			SqlSessionTemplate st = (SqlSessionTemplate) getSqlSession();
			newSession = SqlSessionUtils.getSqlSession(st.getSqlSessionFactory(), st.getExecutorType(), st.getPersistenceExceptionTranslator());
			conn = newSession.getConnection();

			MappedStatement mst = getSqlSession().getConfiguration().getMappedStatement(statement);
			BoundSql boundSql = mst.getBoundSql(parameter);

			String sql = " select count(1) total_count from (" + SQLHelper.removeOrders(boundSql.getSql()) + ") t";

			pstmt = conn.prepareStatement(sql);
			DefaultParameterHandler dph = new DefaultParameterHandler(mst, parameter, boundSql);
			dph.setParameters(pstmt);
			rs = pstmt.executeQuery();

			int count = 0;
			if (rs.next()) {
				count = rs.getInt("total_count");
			}
			return count;
		} catch (Exception e) {
			logger.error("===>select Count(" + statement + ") error:", e);
			throw new DbException(e);
		} finally {
			// Close quietly.
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
				}
			}
			if (newSession != null) {
				newSession.close();
			}
		}
	}

}

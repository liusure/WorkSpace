package com.saas.generator.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.sql.DataSource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.saas.generator.domain.GenTable;
import com.saas.generator.domain.GenTableColumn;

@Service
public class DbTableService {

	private static final String SELECT_DB_TABLE_LIST = "select table_name, table_comment, create_time, update_time from information_schema.tables "
			+ "		where table_schema = (select database()) "
			+ "		AND table_name NOT LIKE 'qrtz_%' AND table_name NOT LIKE 'gen_%' "
			+ "		AND table_name NOT IN (select table_name from gen_table) ";

	private static final String SELECT_DB_TABLE_COLUMN_LIST_BY_NAME = "select column_name, (case when (is_nullable = 'no'  && column_key != 'PRI') then '1' else null end) as is_required, (case when column_key = 'PRI' then '1' else '0' end) as is_pk, ordinal_position as sort, column_comment, (case when extra = 'auto_increment' then '1' else '0' end) as is_increment, column_type "
			+ "		from information_schema.columns where table_schema = (select database()) and table_name = ? "
			+ "		order by ordinal_position";

	@Autowired
	private DataSource dataSource;

	public List<GenTable> selectDbTableList() {
		List<GenTable> list = new ArrayList<>();
		Connection conn = null;
		ResultSet rs = null;
		Statement stat = null;
		try {
			conn = dataSource.getConnection();
			stat = conn.createStatement();
			rs = stat.executeQuery(SELECT_DB_TABLE_LIST);
			while (rs.next()) {
				GenTable table = new GenTable();
				table.setTableName(rs.getString(1));
				String tableComment = rs.getString(2);
				table.setTableComment(StringUtils.isEmpty(tableComment) ? table.getTableName() + "默认备注" : tableComment);
				table.setCreateTime(rs.getDate(3));
				table.setUpdateTime(rs.getDate(4));
				list.add(table);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
				}
			}
			if (stat != null) {
				try {
					stat.close();
				} catch (Exception e) {

				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}

		return list;
	}

	public List<GenTable> selectDbTableListByNames(String[] tableNames) {

		List<GenTable> tables = selectDbTableList();
		return tables.stream().filter(table -> StringUtils.equalsAnyIgnoreCase(table.getTableName(), tableNames))
				.collect(Collectors.toList());
	}

	public List<GenTableColumn> selectDbTableColumnsByName(String tableName) {
		List<GenTableColumn> list = new ArrayList<>();
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement stat = null;
		try {
			conn = dataSource.getConnection();
			stat = conn.prepareStatement(SELECT_DB_TABLE_COLUMN_LIST_BY_NAME);
			stat.setString(1, tableName);
			rs = stat.executeQuery();
			while (rs.next()) {
				GenTableColumn column = new GenTableColumn();
				column.setColumnName(rs.getString(1));
				column.setIsRequired(rs.getString(2));
				column.setIsPk(rs.getString(3));
				column.setSort(rs.getInt(4));
				column.setColumnComment(rs.getString(5));
				column.setIsIncrement(rs.getString(6));
				column.setColumnType(rs.getString(7));
				list.add(column);
				System.out.println(column);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
				}
			}
			if (stat != null) {
				try {
					stat.close();
				} catch (Exception e) {

				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}

		return list;
	}
}

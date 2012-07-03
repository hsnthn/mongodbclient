package com.hsnthn.mongodbclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import com.hsnthn.mongodbclient.task.Action;



public class App {



	public static void main(String[] args) {
		String sql = "";
		String host = "localhost";
		ArrayList<String> sqlList=new ArrayList<String>();

		try {
			System.out.println("Host: ");
			InputStreamReader converter = new InputStreamReader(System.in);
			BufferedReader in = new BufferedReader(converter);
			host = in.readLine();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		while (true) {
			try {
				System.out
						.println("---------------------------------------------------------------------------");
				System.out.println("Sql: ");
				InputStreamReader converter1 = new InputStreamReader(System.in);
				BufferedReader in1 = new BufferedReader(converter1);
				sql = in1.readLine();
			} catch (Exception e) {
				System.out.println("Error! Exception: " + e);
			}
			if (sql.equals("stop")) {
				break;
			}

			App app = new App();
			Action action=new Action(host);
			sqlList = app.stringProcess(sql);
			if (sqlList.get(0).equals("host")) {
				host=sqlList.get(0);
			} else if (sqlList.get(0).equals("save")) {
				action.save(sqlList);
			} else if (sqlList.get(0).equals("remove")) {
				action.remove(sqlList);
			} else if (sqlList.get(0).equals("update")) {
				action.update(sqlList);
			} else if (sqlList.get(0).equals("read")){
				action.read(sqlList.get(1), sqlList.get(2));
			}
		}

	}
	
	
	public ArrayList<String> stringProcess(String sql){
		ArrayList<String> sqlList=new ArrayList<String>();
		String[] sqlArray=sql.split(" ");
		for(String var:sqlArray){
			String sqlNewArray[]=var.split(":");
			for(String var1:sqlNewArray){
				sqlList.add(var1);
			}
		}
		
		
		return sqlList;
	}

	

}

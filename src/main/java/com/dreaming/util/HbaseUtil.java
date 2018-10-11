package com.dreaming.util;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Message:
 * <p>
 * Content:
 *
 * @author lucky
 * create on 08/10/2018
 */
public class HbaseUtil {

    private static  Configuration configuration;

    static  {
        configuration = HBaseConfiguration.create();
        configuration.addResource("properties/hbase-site.xml");
//        configuration.set("hbase.zookeeper.quorum", "192.168.13.94");
//        configuration.set("hbase.zookeeper.property.clientPort", "2181");
    }

    public static void main(String[] args) {
//        createTable("test1");
//        deleteTable("test1");
//        putData("student");
//        showTables();
//        scanTable("student");
//        getByRowKey("student","rowkey_9");
//        ConcurrentHashMap
        getByRowKeyAndColumns("student","1","f1","name");
    }

    private static void getByRowKeyAndColumns(String tname, String rowkey, String family,String column) {
        try {
            HTable hTable = new HTable(configuration,tname);
            Get get = new Get(rowkey.getBytes());
            get.addColumn(family.getBytes(),column.getBytes());
            Result result = hTable.get(get);
            CellScanner cellScanner = result.cellScanner();
            while (cellScanner.advance()){
                Cell cell = cellScanner.current();
                String value = new String(CellUtil.cloneValue(cell));
                System.err.println("值为："+value);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除表
     * @param tableName
     */
    private static void deleteTable(String tableName){
        try {
            HBaseAdmin hBaseAdmin = new HBaseAdmin(configuration);
            if(hBaseAdmin.tableExists(tableName)){
                hBaseAdmin.disableTable(tableName);
                hBaseAdmin.deleteTable(tableName);
            }else {
                System.out.println("Table: "+tableName+" 不存在");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 插入数据
     * @param tableName
     */
    private static void putData(String tableName){
        //通过表名获取tbName
        //通过connection获取相应的表
        HTable table = null;
        try {
            table = new HTable(configuration,tableName);
            //创建Random对象以作为随机参数
            Random random = new Random();
            //hbase支持批量写入数据，创建Put集合来存放批量的数据
            List<Put> batput = new ArrayList<>();
            //循环10次，创建10组测试数据放入list中
            for(int i=0;i<10;i++){
                //实例化put对象，传入行键
                Put put =new Put(Bytes.toBytes("rowkey_"+i));
                //调用addcolum方法，向i簇中添加字段
                put.addColumn(Bytes.toBytes("f1"), Bytes.toBytes("username"),Bytes.toBytes("un_"+i));
                put.addColumn(Bytes.toBytes("f1"), Bytes.toBytes("age"),Bytes.toBytes(random.nextInt(50)+1));
                put.addColumn(Bytes.toBytes("f1"), Bytes.toBytes("birthday"),Bytes.toBytes("2017"+i));
                put.addColumn(Bytes.toBytes("f1"), Bytes.toBytes("phone"),Bytes.toBytes("phone:"+i));
                put.addColumn(Bytes.toBytes("f1"), Bytes.toBytes("邮箱"),Bytes.toBytes("邮箱:"+i));
                //将测试数据添加到list中
                batput.add(put);
            }
            //调用put方法将list中的测试数据写入hbase
            table.put(batput);
            System.out.println("数据插入完成！");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    private static void getByRowKey(String tableName,String... rowKeys){
        //通过tbName获得Table对象
        HTable table = null;
        try {
            table = new HTable(configuration,tableName);
            //创建Get的集合以承接查询的条件
            List<Get> gets = new ArrayList<>();
            //循环五次，取前五个测试数据
            for(int i=0;i<rowKeys.length;i++){
                //就将查询条件放入get对象中
                Get get = new Get(Bytes.toBytes(rowKeys[i]));
                //将get对象放入聚合
                gets.add(get);
            }
            //调用table.get方法传入查询条件，获得查询的结果的数组
            Result[] results = table.get(gets);
            //遍历结果数组，利用CellScanner配合cellUtil获得对应的数据
            for (Result result : results) {
                //调用result.cellscanner创建scanner对象
                CellScanner cellScanner = result.cellScanner();
                //遍历结果集，取出查询结果，
                //如果存在下一个cell则advandce方法返回true，且current方法会返回一个有效的cell，可以当作循环条件
                while (cellScanner.advance()) {
                    //current方法返回一个有效的cell
                    Cell cell = cellScanner.current();
                    //使用CellUtil调用相应的方法获取想用的数据，并利用Bytes.toString方法将结果转换为string输出
                    String family = Bytes.toString(CellUtil.cloneFamily(cell));
                    String qualify = Bytes.toString(CellUtil.cloneQualifier(cell));
                    String rowkey = Bytes.toString(CellUtil.cloneRow(cell));
                    String value = Bytes.toString(CellUtil.cloneValue(cell));
                    System.err.println(family+"\t"+qualify+"\t"+rowkey+"\t"+value);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
      }

    /**
     * 遍历表中所有数据
     * @param tableName
     */
    private static void scanTable(String tableName){
        try {
            HTable hTable = new HTable(configuration, tableName);
            Scan scan = new Scan();
            ResultScanner results = hTable.getScanner(scan);
            for (Result result:results){
                NavigableMap<byte[], NavigableMap<byte[], NavigableMap<Long, byte[]>>> map = result.getMap();
                for(byte[] cf : map.keySet()){
                    NavigableMap<byte[], NavigableMap<Long, byte[]>> valueWithColumnQualify = map.get(cf);
                    for(byte[] columnQualify:valueWithColumnQualify.keySet()){
                        NavigableMap<Long, byte[]> valueWithTimestamp = valueWithColumnQualify.get(columnQualify);
                        for (Long ts : valueWithTimestamp.keySet()) {
                            byte[] value = valueWithTimestamp.get(ts);
                            String rowKey = Bytes.toString(result.getRow());
                            String columnFamily = Bytes.toString(cf);
                            String columnqualify = Bytes.toString(columnQualify);
                            String timestamp =new Date(ts)+"";
                            String values = Bytes.toString(columnQualify);
                            System.err.println(rowKey+"\t"+columnFamily+"\t"+columnqualify+"\t"+timestamp+"\t"+values);
                        }
                    }
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 查询所有表名
     */
    private static void showTables(){
        try {
            HBaseAdmin hBaseAdmin = new HBaseAdmin(configuration);
            HTableDescriptor[] tableDescriptor =hBaseAdmin.listTables();
            for (HTableDescriptor hTableDescriptor:tableDescriptor){
                System.out.println(hTableDescriptor.getNameAsString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * hbase 创建表
     * @param tableName
     */
    private static void createTable(String tableName) {
        System.out.println("start create table ......");
        try {
            HBaseAdmin hBaseAdmin = new HBaseAdmin(configuration);
            if (hBaseAdmin.tableExists(tableName)) {// 如果存在要创建的表，那么先删除，再创建
                hBaseAdmin.disableTable(tableName);
                hBaseAdmin.deleteTable(tableName);
                System.out.println(tableName + " is exist,detele....");
            }
            HTableDescriptor tableDescriptor = new HTableDescriptor(tableName);
            tableDescriptor.addFamily(new HColumnDescriptor("column1"));
            hBaseAdmin.createTable(tableDescriptor);
            System.out.println(tableName + "创建成功");
        } catch (MasterNotRunningException e) {
            e.printStackTrace();
        } catch (ZooKeeperConnectionException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("end create table ......");
    }
}

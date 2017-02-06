package com.cqliving.codegenerator.ui;

import com.cqliving.codegenerator.DefaultCodeGeneratorFactory;
import com.cqliving.codegenerator.ui.jdbc.JDBCAdapter;
import com.cqliving.codegenerator.ui.jdbc.JDBConnect;
import com.cqliving.codegenerator.ui.jdbc.dialect.MetadataDialect;
import com.cqliving.codegenerator.ui.jdbc.dialect.MysqlMetadataDialectImpl;
import com.cqliving.codegenerator.ui.jdbc.dialect.OracleMetadataDialectImpl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 15-1-30
 * Time: 上午9:21
 * To change this template use File | Settings | File Templates.
 */

@SuppressWarnings("rawtypes")
public class Generator extends JPanel {

    /**
	 * 
	 */
	private static final long serialVersionUID = 5547975840264977644L;
	private static final int PREFERRED_WIDTH = 1000;
    private static final int PREFERRED_HEIGHT = 640;

    private JDBConnect jdbConnect;

    private JDBCAdapter dt;

    private MetadataDialect metadataDialect;

    public static Dimension HGAP30 = new Dimension(30,1);

    public static Dimension VGAP10 = new Dimension(1,10);

    EmptyBorder border5 = new EmptyBorder(5,5,5,5);
    EmptyBorder border10 = new EmptyBorder(10,10,10,10);

    private TablesListModel listModel;

    //private JFrame frame;

    private ResourceBundle bundle;

    private JPanel mainPanel;

    private JPanel leftPanel;

    private JPanel centerPanel;

	private JComboBox driverCombo;

    private JTextField urlText;

    private JTextField userNameText;

    private JTextField pwdText;

    private JTextField workspaceField;

    private JTextField packageField;

    private JTextField webWorkspaceField;

    private JTextField ctrPackageField;

    private JTextField mappingField;

    private JTextField jspPath;

    private JTextField jspPrefix;

    private JTextField tilesPrefix;



    private JLabel label;

    private JList list;

    private JCheckBox domainBox;
    private JCheckBox managerBox;
    private JCheckBox serviceBox;
    private JCheckBox daoBox;
    private JCheckBox controllerBox;
    private JCheckBox jspBox;

    @SuppressWarnings("serial")
	public Generator() {
        setLayout(new BorderLayout());

        setPreferredSize(new Dimension(PREFERRED_WIDTH,PREFERRED_HEIGHT));

        add(new JLabel("NORTH"), BorderLayout.NORTH);
        //add(new JLabel("WEST"), BorderLayout.WEST);
        add(new JLabel("CENTER"), BorderLayout.CENTER);
        //add(new JLabel("EAST"), BorderLayout.EAST);
        //add(new JLabel("SOUTH"), BorderLayout.SOUTH);

        //主窗口
        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));
        //add(mainPanel);

        mainPanel.add(Box.createRigidArea(HGAP30));


        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitPane.setContinuousLayout(true);
        splitPane.setOneTouchExpandable(true);
        add(splitPane);

        //左边
        leftPanel = new JPanel();
        leftPanel.setLayout(new BorderLayout());
        splitPane.add(leftPanel);

        leftPanel.setBorder(border10);
        leftPanel.add(getDBSetting(), BorderLayout.NORTH);
        leftPanel.add(getWestTables(), BorderLayout.CENTER);

        //mainPanel.add(Box.createRigidArea(HGAP30));
        //中间
        centerPanel = new JPanel(){};
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        splitPane.add(centerPanel);

        //中上
        JPanel webSettingPanel = new JPanel(new GridLayout(1, 2));
        centerPanel.add(webSettingPanel);
        webSettingPanel.setBorder(border5);

        //web 设置
        webSettingPanel.add(getWebSetting());


        JPanel ctrPanel = new JPanel(new GridLayout(2, 1));
        //ctrPanel.setLayout(new BoxLayout(ctrPanel, BoxLayout.Y_AXIS));
        webSettingPanel.add(ctrPanel);


        ctrPanel.add(getServiceSetting());

        ctrPanel.add(getCheckboxGenerator());

        JButton jButton = new JButton(getString("but.generation"));
        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int result = JOptionPane.showConfirmDialog(mainPanel, "确定生成吗？\n你将为["+list.getSelectedValue().toString()+"]表生成代码！");
                if(result == JOptionPane.YES_OPTION) {
                    //保存设置
                    saveSetting();
                    //生成
                    try{
                        String dbUrl=urlText.getText();
                        String database = dbUrl.substring(dbUrl.lastIndexOf("/")+1, dbUrl.indexOf("?"));
                        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-main.xml");
                        DefaultCodeGeneratorFactory codeGeneratorFactory = (DefaultCodeGeneratorFactory) context.getBean("codeGeneratorFactory");
                        codeGeneratorFactory.generateTables(database,getTableName());
                        JOptionPane.showMessageDialog(mainPanel, "代码生成成功！");
                    }catch (Exception ex){
                        JOptionPane.showMessageDialog(mainPanel, "代码生成失败！\n"+ex.getMessage());
                    }
                }

            }
        });

        JPanel jPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        jPanel.add(new JLabel(getString("desc.generation")));
        jPanel.add(jButton);
        centerPanel.add(jPanel);


        //表格
        centerPanel.add(Box.createRigidArea(VGAP10));
        centerPanel.add(getTable());
        centerPanel.setBorder(border5);

        //加载配置数据
        loadSetting();

    }

    public String getString(String key) {
        String value = "nada";
        if(bundle == null) {
            bundle = ResourceBundle.getBundle("i18n.message");
        }
        try {
            value = bundle.getString(key);
        } catch (MissingResourceException e) {
            System.out.println("java.util.MissingResourceException: Couldn't find value for: " + key);
        }
        return value;
    }

    public class TablesListModel extends AbstractListModel {
		private static final long serialVersionUID = 3513164369701949304L;
		private List dataList = Arrays.asList("");

        public void changedList(List dataList){
            this.dataList = dataList;
            this.fireContentsChanged(this, 0, dataList.size());
        }

        @Override
        public int getSize() {
            return dataList.size();
        }

        @Override
        public Object getElementAt(int index) {
            return dataList.get(index);
        }
    }

    @SuppressWarnings("unchecked")
	public JComponent getWestTables(){
        list = new JList();
        listModel = new TablesListModel();
        list.setModel(listModel);

        list.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if(e.getValueIsAdjusting()==false){
                    String sql="select COLUMN_NAME as name, COLUMN_COMMENT AS comments, IS_NULLABLE AS nullable\n" +
                            "from information_schema.COLUMNS where table_name='"+getTableName()+"';";
                    sql = metadataDialect.colSql(getTableName());
                    dt.executeQuery(jdbConnect.getConnection(), jdbConnect.getStatement(), sql);
                    //System.out.println(list.getSelectedValue());
                }
            }
        });
        list.setVisibleRowCount(22);
        // Add list to a scrollpane
        JScrollPane scrollPane = new JScrollPane(list);

        return scrollPane;
    }


    @SuppressWarnings({ "serial", "unchecked" })
	public JComponent getDBSetting(){
        JPanel box = new JPanel();
        box.setLayout(new BoxLayout(box, BoxLayout.X_AXIS));

        JPanel liblePanel = new JPanel(new GridLayout(0, 1)){
            public Dimension getMaximumSize() {
                return new Dimension(300, super.getMaximumSize().height);
            }
        };
        JPanel textPanel = new JPanel(new GridLayout(0, 1));

        //驱动
        driverCombo = new JComboBox(new String[]{"com.mysql.jdbc.Driver", "oracle.jdbc.driver.OracleDriver"});

        label = new JLabel(getString("db.driver"), JLabel.RIGHT);
        label.setLabelFor(driverCombo);

        liblePanel.add(label);
        textPanel.add(driverCombo);

        //jdbc连接串
        urlText = new JTextField();
        //urlText.setColumns(50);

        label = new JLabel(getString("db.url"), JLabel.RIGHT);
        label.setLabelFor(urlText);

        liblePanel.add(label);
        textPanel.add(urlText);

        //用户名
        userNameText = new JTextField();
        //userNameText.setColumns(10);

        label = new JLabel(getString("db.username"), JLabel.RIGHT);
        label.setLabelFor(userNameText);

        liblePanel.add(label);
        textPanel.add(userNameText);

        //密码
        pwdText = new JTextField();
        //pwdText.setColumns(10);

        label = new JLabel(getString("db.password"), JLabel.RIGHT);
        label.setLabelFor(pwdText);

        liblePanel.add(label);
        textPanel.add(pwdText);

        JButton connBut = new JButton(getString("but.conn"));
        connBut.addActionListener(new ActionListener() {
            @SuppressWarnings("unchecked")
			@Override
            public void actionPerformed(ActionEvent e) {

                String dbUrl=urlText.getText();
                if (StringUtils.contains(dbUrl, ":mysql:")) {
                    metadataDialect = new MysqlMetadataDialectImpl(dbUrl);
                } else if (StringUtils.contains(dbUrl, ":oracle:")) {
                    metadataDialect = new OracleMetadataDialectImpl();
                } else {
                    throw new IllegalArgumentException("Unknown Database of " + dbUrl);
                }
                String driver = driverCombo.getSelectedItem().toString();
                String user = userNameText.getText();
                String passwd = pwdText.getText();

                jdbConnect = new JDBConnect(dbUrl, driver, user, passwd);
                Vector<Map> vector = jdbConnect.executeQuery(metadataDialect.tablesSql());
                List list = new LinkedList();
                for(Map map : vector){
                    list.add(map.get("table_name") + (map.get("table_comment") == null ? "" : "    << "+map.get("table_comment").toString()+" >>"));
                }
                listModel.changedList(list);

                //保存设置
                saveDBSetting();
            }
        });
        liblePanel.add(new JLabel());
        textPanel.add(connBut);
        //布局
        box.add(liblePanel);
        box.add(textPanel);
        return box;
    }


    public JComponent getTable(){
        dt = new JDBCAdapter(new String[]{"查询条件", "列表显示", "编辑", "查看"}, new Object[]{false, false, true, true});
        /*dt.executeQuery("select COLUMN_NAME as name, COLUMN_COMMENT AS comments, IS_NULLABLE AS nullable\n" +
                "from information_schema.COLUMNS where table_name='"+tableName+"';");*/

        // Create the table
        final JTable tableView = new JTable(dt);

        tableView.getSelectedRow();

        tableView.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if(e.getValueIsAdjusting()==false){
                }
            }
        });

        JScrollPane scrollpane = new JScrollPane(tableView);
        scrollpane.setPreferredSize(new Dimension(700, 300));

        return scrollpane;
    }


    public JComponent getWebSetting(){
        JPanel webPanel = new JPanel();/*{
            public Dimension getMaximumSize() {
                return new Dimension(super.getMaximumSize().width, 220);
            }
        };*/

        webPanel.setLayout(new BoxLayout(webPanel, BoxLayout.Y_AXIS));
        webPanel.setBorder(new TitledBorder(getString("setting.web.title")));

        JPanel jPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        label = new JLabel(getString("setting.web.work"));
        webWorkspaceField = new JTextField();
        webWorkspaceField.setColumns(30);
        jPanel.add(label);
        jPanel.add(webWorkspaceField);
        webPanel.add(jPanel);


        jPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        label = new JLabel(getString("setting.web.ctr.pack"));
        ctrPackageField = new JTextField();
        ctrPackageField.setColumns(30);
        jPanel.add(label);
        jPanel.add(ctrPackageField);
        webPanel.add(jPanel);

        jPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        label = new JLabel(getString("setting.web.url"));
        mappingField = new JTextField();
        mappingField.setColumns(30);
        jPanel.add(label);
        jPanel.add(mappingField);
        webPanel.add(jPanel);



        jPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        label = new JLabel(getString("setting.web.jsp.path"));
        jspPath = new JTextField();
        jspPath.setColumns(30);
        jPanel.add(label);
        jPanel.add(jspPath);
        webPanel.add(jPanel);

        jPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        label = new JLabel(getString("setting.web.jsp.prefix"));
        jspPrefix = new JTextField();
        jspPrefix.setColumns(30);
        jPanel.add(label);
        jPanel.add(jspPrefix);
        webPanel.add(jPanel);

        jPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        label = new JLabel(getString("setting.web.tiles.prefix"));
        tilesPrefix = new JTextField();
        tilesPrefix.setColumns(30);
        jPanel.add(label);
        jPanel.add(tilesPrefix);
        webPanel.add(jPanel);

        return webPanel;
    }

    public JComponent getServiceSetting(){
        JPanel servicePanel = new JPanel();
        servicePanel.setBorder(new TitledBorder(getString("setting.service.title")));
        servicePanel.setLayout(new BoxLayout(servicePanel, BoxLayout.Y_AXIS));

        JPanel jPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        label = new JLabel(getString("setting.service.work"));
        workspaceField = new JTextField();
        workspaceField.setColumns(30);
        jPanel.add(label);
        jPanel.add(workspaceField);
        servicePanel.add(jPanel);

        jPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        label = new JLabel(getString("setting.service.pack"));
        packageField = new JTextField();
        packageField.setColumns(30);
        jPanel.add(label);
        jPanel.add(packageField);
        servicePanel.add(jPanel);

        return servicePanel;
    }

    public JComponent getCheckboxGenerator(){

        JPanel checkboxPanel = new JPanel();
        checkboxPanel.setLayout(new BoxLayout(checkboxPanel, BoxLayout.Y_AXIS));
        checkboxPanel.setBorder(new TitledBorder(getString("setting.options.title")));
        domainBox = new JCheckBox("domian");
        managerBox = new JCheckBox("manager");
        serviceBox = new JCheckBox("service");
        daoBox = new JCheckBox("dao");
        controllerBox = new JCheckBox("controller");
        jspBox = new JCheckBox("jsp");

        checkboxPanel.add(domainBox);
        checkboxPanel.add(managerBox);
        checkboxPanel.add(serviceBox);
        checkboxPanel.add(daoBox);
        checkboxPanel.add(controllerBox);
        checkboxPanel.add(jspBox);

        return checkboxPanel;
    }


    private String listOfString(List<String> list){
        if(list==null || list.size()<=0){
            return "";
        }

        StringBuffer strbuf = new StringBuffer();
        for(String str : list){
            strbuf.append(str).append(",");
        }
        strbuf.setLength(strbuf.length()-1);

        return strbuf.toString();
    }


    private void saveDBSetting(){
        Map<String, String> map = new HashMap<String, String>();

        map.put("jdbc.url", urlText.getText());
        map.put("jdbc.username", userNameText.getText());
        map.put("jdbc.password", pwdText.getText());
        map.put("jdbc.driver", driverCombo.getSelectedItem().toString());

        ConfigFileHelper.saveProperties(map);
    }

    @SuppressWarnings("unchecked")
	private void saveSetting(){
        Map<String, String> map = new HashMap<String, String>();
        map.put("generator.workspace", workspaceField.getText());
        map.put("generator.rootPackage", packageField.getText());

        map.put("generator.web.workspace", webWorkspaceField.getText());
        map.put("generator.web.package", ctrPackageField.getText());
        map.put("generator.page.mapping", mappingField.getText());

        map.put("generator.pagePath", jspPath.getText());
        map.put("generator.page.prefix", jspPrefix.getText());
        map.put("generator.page.tilesName", tilesPrefix.getText());

        //optios
        StringBuffer options = new StringBuffer();
        if(domainBox.isSelected()){
            options.append("domain").append(",");
        }

        if(daoBox.isSelected()){
            options.append("dao").append(",");
        }
        
        if(managerBox.isSelected()){
            options.append("manager").append(",");
        }
        
        if(serviceBox.isSelected()){
            options.append("service").append(",");
        }

        if(controllerBox.isSelected()){
            options.append("controller").append(",");
        }

        if(jspBox.isSelected()){
            options.append("page").append(",");
        }
        if(options.length()>=1){
            options.setLength(options.length()-1);
        }
        map.put("generator.options", options.toString());

        //
        Vector rows = dt.getRows();
        List searchList = new ArrayList();
        List listList = new ArrayList();
        List detailList = new ArrayList();
        List viewList = new ArrayList();
        for(int j=0; j<rows.size(); j++){
            Vector col = (Vector) rows.get(j);
            if((Boolean) col.get(3)){
                //查询
                searchList.add(col.get(0));
            }

            if((Boolean) col.get(4)){
                //列表
                listList.add(col.get(0));
            }

            if((Boolean) col.get(5)){
                //编辑
                detailList.add(col.get(0));
            }

            if((Boolean) col.get(6)){
                //查看
                viewList.add(col.get(0));
            }
        }
        map.put("generator.page.searchColumns", listOfString(searchList));
        map.put("generator.page.listColumns", listOfString(listList));
        map.put("generator.page.detailColumns", listOfString(detailList));
        map.put("generator.page.viewColumns", listOfString(viewList));

        ConfigFileHelper.saveProperties(map);
    }


    private void loadSetting(){
        Properties p = ConfigFileHelper.loadProperty();
        urlText.setText(p.getProperty("jdbc.url"));
        userNameText.setText(p.getProperty("jdbc.username"));
        pwdText.setText(p.getProperty("jdbc.password"));
        driverCombo.setSelectedItem(p.getProperty("jdbc.driver"));


        workspaceField.setText(p.getProperty("generator.workspace"));
        packageField.setText(p.getProperty("generator.rootPackage"));


        webWorkspaceField.setText(p.getProperty("generator.web.workspace"));
        ctrPackageField.setText(p.getProperty("generator.web.package"));
        mappingField.setText(p.getProperty("generator.page.mapping"));

        jspPath.setText(p.getProperty("generator.pagePath"));
        jspPrefix.setText(p.getProperty("generator.page.prefix"));
        tilesPrefix.setText(p.getProperty("generator.page.tilesName"));

        String options = p.getProperty("generator.options");
        if(StringUtils.isNotBlank(options)){
            for(String str : options.split(",")){
                if("domain".equalsIgnoreCase(str.trim())){
                    domainBox.setSelected(true);
                } else if("dao".equalsIgnoreCase(str.trim())){
                    daoBox.setSelected(true);
                } else if("manager".equalsIgnoreCase(str.trim())){
                    managerBox.setSelected(true);
                } else if("service".equalsIgnoreCase(str.trim())){
                    serviceBox.setSelected(true);
                } else if("controller".equalsIgnoreCase(str.trim())){
                    controllerBox.setSelected(true);
                } else if("page".equalsIgnoreCase(str.trim())){
                    jspBox.setSelected(true);
                }
            }

        }
    }

    private String getTableName(){
        if(list.getSelectedValue()==null)
            return null;

        return list.getSelectedValue().toString().split("<<")[0].trim();
    }


    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setTitle("e-order code generator");
        frame.getContentPane().add(new Generator());
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }


}

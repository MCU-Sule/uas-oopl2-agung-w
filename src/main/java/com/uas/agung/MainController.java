package com.uas.agung;

import com.uas.agung.dao.FeMemberDao;
import com.uas.agung.dao.FePointDao;
import com.uas.agung.dao.FeTransactionDao;
import com.uas.agung.entity.FeMember;
import com.uas.agung.entity.FePoint;
import com.uas.agung.entity.FeTransaction;
import com.uas.agung.util.YSAalert;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MainController {

    public Button btnSave;
    public TableView<FeMember> tableView;
    public TableColumn<FeMember,String> col1;
    public TableColumn<FeMember,String> col2;
    public TableColumn<FeMember,String> col3;
    public TableColumn<FeMember,String> col4;

    public TableView<FeTransaction> tableView1;
    public TableColumn<FeTransaction,String> col11;
    public TableColumn<FeTransaction,String> col21;

    public TableView<FePoint> tableView2;
    public TableColumn<FePoint,String> col12;
    public TableColumn<FePoint,String> col22;


    public Button btnReset;
    public Button btnUpdate;
    public Label totalM;
    public Label totalT;
    public Label totalP;

    private final FeMemberDao fmDao=new FeMemberDao();
    private final FePointDao fpDao=new FePointDao();
    private final FeTransactionDao ftDao=new FeTransactionDao();
    private final ObservableList<FeMember> fmList=fmDao.showData();
    private ObservableList<FeTransaction> ftList= FXCollections.observableArrayList();
    private ObservableList<FePoint> fpList= FXCollections.observableArrayList();
    public TextField txtid;
    public TextField txtname;
    public TextArea txtaddress;
    public TextField txtphone;
    public TextField txtemail;
    public TextField txtusername;
    public DatePicker txtbirth;
    private final YSAalert ysa=new YSAalert();
    public TextField nominal;
    public DatePicker date;

    public void initialize(){
        afterUpdate();
        tableView.setItems(fmList);
        totalM.setText(String.valueOf(fmDao.countData().get(0)));
        col1.setCellValueFactory(data->new SimpleStringProperty(String.valueOf(data.getValue().getCitizenId())));
        col2.setCellValueFactory(data->new SimpleStringProperty(String.valueOf(data.getValue().getName())));
        col3.setCellValueFactory(data->new SimpleStringProperty(String.valueOf(data.getValue().getPhone())));
        col4.setCellValueFactory(data->new SimpleStringProperty(String.valueOf(data.getValue().getBirthdate())));
        tableView.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observableValue, Object o, Object t1) {
                if (tableView.getSelectionModel().getSelectedItem() != null) {
                    String a=tableView.getSelectionModel().getSelectedItem().getCitizenId();
                    String b=tableView.getSelectionModel().getSelectedItem().getName();
                    String c=tableView.getSelectionModel().getSelectedItem().getAddress();
                    String d=tableView.getSelectionModel().getSelectedItem().getPhone();
                    String e=tableView.getSelectionModel().getSelectedItem().getEmail();
                    String f=tableView.getSelectionModel().getSelectedItem().getUsername();
                    Date g=tableView.getSelectionModel().getSelectedItem().getBirthdate();
                    txtid.setText(String.valueOf(a));
                    txtname.setText(b);
                    txtaddress.setText(c);
                    txtphone.setText(d);
                    txtemail.setText(e);
                    txtusername.setText(f);
                    txtbirth.setValue(g.toLocalDate());
                    beforeUpdate();
                    ftList=ftDao.showDataById(itemsNow());
                    tableView1.setItems(ftList);
                    col11.setCellValueFactory(data->new SimpleStringProperty(String.valueOf(data.getValue().getTransDate())));
                    col21.setCellValueFactory(data->new SimpleStringProperty(String.valueOf(data.getValue().getNominal())));
                    fpList=fpDao.showDataById(itemsNow());
                    tableView2.setItems(fpList);
                    col12.setCellValueFactory(data->new SimpleStringProperty(String.valueOf(data.getValue().getId())));
                    col22.setCellValueFactory(data->new SimpleStringProperty(String.valueOf(data.getValue().getValue())));
                    totalT.setText(String.valueOf(ftDao.sumDataById(itemsNow()).get(0)));
                    totalP.setText(String.valueOf(fpDao.sumDataById(itemsNow()).get(0)));
                }
            }
        });

    }
    private void afterUpdate(){
        btnSave.setDisable(false);
        btnReset.setDisable(false);
        btnUpdate.setDisable(true);
        resetAction();
    }
    private FeMember itemsNow(){
        FeMember p=new FeMember();
        p.setCitizenId(tableView.getSelectionModel().getSelectedItem().getCitizenId());
        p.setName(tableView.getSelectionModel().getSelectedItem().getName());
        p.setAddress(tableView.getSelectionModel().getSelectedItem().getAddress());
        p.setPhone(tableView.getSelectionModel().getSelectedItem().getPhone());
        p.setEmail(tableView.getSelectionModel().getSelectedItem().getEmail());
        p.setUsername(tableView.getSelectionModel().getSelectedItem().getUsername());
        p.setBirthdate(tableView.getSelectionModel().getSelectedItem().getBirthdate());
        return p;
    }
    private void beforeUpdate(){
        btnSave.setDisable(true);
        btnReset.setDisable(true);
        btnUpdate.setDisable(false);
    }
    @FXML
    private void saveMemberAction(){
        Alert loading = ysa.showLoading();
        loading.show();
        Task<Void> task=new Task<Void>(){
            @Override
            protected Void call() {
                FeMember i=itemsNow();
                if (!txtid.getText().isEmpty()){
                    int res=fmDao.addData(i);
                    if (res==1){
                        fmList.clear();
                        fmList.addAll(fmDao.showData());
                        tableView.refresh();
                        afterUpdate();
                        resetAction();

                    }
                }return null;
            }
        };
        ExecutorService exService= Executors.newCachedThreadPool();
        exService.execute(task);
        exService.shutdown();
        boolean isFinished= false;
        try {
            isFinished = exService.awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (isFinished){
            loading.close();
        }
    }
    @FXML
    private void resetAction(){
        txtid.clear();
        txtname.clear();
        txtaddress.clear();
        txtphone.clear();
        txtemail.clear();
        txtusername.clear();
        txtbirth.setValue(null);
        nominal.clear();
        date.setValue(null);
    }
    @FXML
    private void updateAction(){
        Alert loading = ysa.showLoading();
        loading.show();
        Task<Void> task=new Task<Void>(){
            @Override
            protected Void call() {
                FeMember i=itemsNow();
                if (!txtid.getText().isEmpty()){
                    int res=fmDao.updateData(i);
                    if (res==1){
                        fmList.clear();
                        fmList.addAll(fmDao.showData());
                        tableView.refresh();
                        afterUpdate();
                        resetAction();

                    }
                }return null;
            }
        };
        ExecutorService exService= Executors.newCachedThreadPool();
        exService.execute(task);
        exService.shutdown();
        boolean isFinished= false;
        try {
            isFinished = exService.awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (isFinished){
            loading.close();
        }
    }
    @FXML
    private void saveTransAction(){
        Alert loading = ysa.showLoading();
        loading.show();
        Task<Void> task=new Task<Void>(){
            @Override
            protected Void call() {
                FeTransaction i=new FeTransaction();
                i.setNominal(Long.parseLong(nominal.getText()));
                i.setTransDate(Date.valueOf(date.getValue()));
                i.setFeMemberByMemberCitizenId(itemsNow());
                if (!txtid.getText().isEmpty()){
                    int res=ftDao.addData(i);
                    if (res==1){
                        fmList.clear();
                        fmList.addAll(fmDao.showData());
                        tableView.refresh();
                        afterUpdate();
                        resetAction();

                    }
                }return null;
            }
        };
        ExecutorService exService= Executors.newCachedThreadPool();
        exService.execute(task);
        exService.shutdown();
        boolean isFinished= false;
        try {
            isFinished = exService.awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (isFinished){
            loading.close();
        }
    }



}

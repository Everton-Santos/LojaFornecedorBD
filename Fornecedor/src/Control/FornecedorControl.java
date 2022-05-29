package Control;

import DAO.FornecedoresDAO;
import DAO.FornecedoresDAOimpl;
import Entity.Fornecedor;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class FornecedorControl {
    private ObservableList<Fornecedor> fornecedor = FXCollections.observableArrayList();

    private StringProperty nome = new SimpleStringProperty("");
    private ObjectProperty<LocalDate> data = new SimpleObjectProperty<>();

    private FornecedoresDAO dao = new FornecedoresDAOimpl();

    private TableView<Fornecedor> table = new TableView<>();

    public StringProperty nomeProperty() {
        return nome;
    }
    public ObjectProperty<LocalDate> dataProperty() {
        return data;
    }

    public FornecedorControl() {
        TableColumn<Fornecedor, String> col1 = new TableColumn<>("Nome");
        col1.setCellValueFactory(new PropertyValueFactory<>("nome"));
        TableColumn<Fornecedor, String> col2 = new TableColumn<>("Date");
        // col2.setCellValueFactory(new PropertyValueFactory<>("data"));
        col2.setCellValueFactory((itemData)-> {
            LocalDate dt = itemData.getValue().getData();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            return new ReadOnlyStringWrapper(dt.format(formatter));
        });

        table.getColumns().addAll(col1, col2);

        table.setItems(fornecedor);
    }

    public void adicionar() {
        System.out.println("Nome: " + nome.get());
        System.out.println("Data: " + data.get());
        Fornecedor e = new Fornecedor();
        e.setNome(nome.get());
        e.setData(data.get());
        fornecedor.add(e);
        dao.inserir(e);
    }

    public void pesquisar() {
//        for (Fornecedor e : fornecedores) {
//            if (e != null && e.getNome().contains(nome.get())) {
//                nome.set(e.getNome());
//                data.set(e.getData());
//                break;
//            }
//        }
        List<Fornecedor> lista = dao.consultar(nome.get());
        fornecedor.clear();
        fornecedor.addAll(lista);
    }
    public TableView getTable() {
        return table;
    }
}

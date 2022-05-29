package Control;

import DAO.LojasDAO;
import DAO.LojasDAOimpl;
import Entity.Loja;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class LojaControl {
    private ObservableList<Loja> loja = FXCollections.observableArrayList();

    private StringProperty nome = new SimpleStringProperty("");
    private ObjectProperty<LocalDate> data = new SimpleObjectProperty<>();

    private LojasDAO dao = new LojasDAOimpl();

    private TableView<Loja> table = new TableView<>();

    public StringProperty nomeProperty() {
        return nome;
    }
    public ObjectProperty<LocalDate> dataProperty() {
        return data;
    }

    public LojaControl() {
        TableColumn<Loja, String> col1 = new TableColumn<>("Nome");
        col1.setCellValueFactory(new PropertyValueFactory<>("nome"));
        TableColumn<Loja, String> col2 = new TableColumn<>("Date");
        // col2.setCellValueFactory(new PropertyValueFactory<>("data"));
        col2.setCellValueFactory((itemData)-> {
            LocalDate dt = itemData.getValue().getData();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            return new ReadOnlyStringWrapper(dt.format(formatter));
        });

        table.getColumns().addAll(col1, col2);

        table.setItems(loja);
    }

    public void adicionar() {
        System.out.println("Nome: " + nome.get());
        System.out.println("Data: " + data.get());
        Loja e = new Loja();
        e.setNome(nome.get());
        e.setData(data.get());
        loja.add(e);
        dao.inserir(e);
    }

    public void pesquisar() {
//        for (Loja e : lojas) {
//            if (e != null && e.getNome().contains(nome.get())) {
//                nome.set(e.getNome());
//                data.set(e.getData());
//                break;
//            }
//        }
        List<Loja> lista = dao.consultar(nome.get());
        loja.clear();
        loja.addAll(lista);
    }
    public TableView getTable() {
        return table;
    }
}


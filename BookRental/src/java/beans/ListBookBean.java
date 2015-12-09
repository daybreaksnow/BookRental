package beans;

import com.sun.xml.rpc.processor.modeler.j2ee.xml.emptyType;
import dao.BookDao;
import entity.BrBook;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 書籍登録画面のバッキングビーン
 * @author T.IMAIZUMI
 * @since 2015/12/09
 */
@Named
@RequestScoped
public class ListBookBean {
    @EJB
    private BookDao dao;

    public List<BrBook> getAll(){
        return dao.findAll();
    }
    
}

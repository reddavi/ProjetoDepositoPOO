import org.example.Item;
import org.example.ItemInexistenteException;
import org.example.SistemaDeposito;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.List;

    public class testePrograma {
        @Test
        public void testCadastrarItem() throws IOException {
            SistemaDeposito deposito = new SistemaDeposito();
            Item item = new Item("FOGÃO", 10);
            deposito.cadastrarItem(item);
            List<Item> itens = deposito.listarItens();
            assertTrue(itens.contains(item));
        }

          @Test
        public void testPesquisarItemExistente() throws ItemInexistenteException, IOException {
         SistemaDeposito deposito = new SistemaDeposito();
         //Cadastrando a geladeira
        Item n1 = new Item("GELADEIRA", 20);
        deposito.cadastrarItem(n1);
        System.out.println(deposito.pesquisarItem("GELADEIRA"));
        assertEquals("GELADEIRA",n1.getNome());
        }

        @Test
        public void testRemoverItemExistente() throws IOException {
            SistemaDeposito deposito = new SistemaDeposito();
            Item item = new Item("ARMARIO", 40);
            deposito.cadastrarItem(item);
            assertTrue(deposito.removerItem("ARMARIO"));
            List<Item> itens = deposito.listarItens();
            assertFalse(itens.contains(item));
        }
    }


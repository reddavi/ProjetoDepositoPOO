import org.example.Item;
import org.example.ItemInexistenteException;
import org.example.SistemaDeposito;
import org.example.tipoEstoqueCategoria;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.List;

    public class testePrograma {
       @Test
        public void testCadastrarItem() throws IOException {
            SistemaDeposito deposito = new SistemaDeposito();
            Item item = new Item("FOGÃO", 10,tipoEstoqueCategoria.ELETRODOMESTICO);
            deposito.cadastrarItem(item);
            List<Item> itens = deposito.listarItens();
            assertTrue(itens.contains(item));
        }

          @Test
        public void testPesquisarItemExistente() throws ItemInexistenteException, IOException {
         SistemaDeposito deposito = new SistemaDeposito();
         //Cadastrando a geladeira
        Item n1 = new Item("GELADEIRA", 20, tipoEstoqueCategoria.ELETRONICO);
        deposito.cadastrarItem(n1);
        System.out.println(deposito.pesquisarItem("GELADEIRA"));
        assertEquals("GELADEIRA",n1.getNome());
        }

        @Test
        public void testRemoverItemExistente() throws IOException {
            SistemaDeposito deposito = new SistemaDeposito();
            Item item = new Item("ARMARIO", 40,tipoEstoqueCategoria.MOVEL);
            deposito.cadastrarItem(item);
            assertTrue(deposito.removerItem("ARMARIO"));
            List<Item> itens = deposito.listarItens();
            assertFalse(itens.contains(item));
        }

        @Test
        public void contarQuantidadeDoEstoque() throws IOException {
            SistemaDeposito deposito = new SistemaDeposito();

            deposito.cadastrarItem(new Item("ARMARIO", 3,tipoEstoqueCategoria.MOVEL));
            deposito.cadastrarItem(new Item("GELADEIRA", 2,tipoEstoqueCategoria.ELETRODOMESTICO));
            deposito.cadastrarItem(new Item("MICROONDAS", 1,tipoEstoqueCategoria.ELETRODOMESTICO));

            System.out.println(deposito.listarItens());

            int quantidadeItens = deposito.contarQuantidadeDoEstoque();

            assertEquals(6,quantidadeItens);
        }
        @Test
        public void testBuscarPorCategoria() throws IOException {
            SistemaDeposito sistemaDeposito = new SistemaDeposito();
            sistemaDeposito.cadastrarItem(new Item("Geladeira", 2, tipoEstoqueCategoria.ELETRODOMESTICO));
            sistemaDeposito.cadastrarItem(new Item("TV", 1, tipoEstoqueCategoria.ELETRONICO));
            sistemaDeposito.cadastrarItem(new Item("Mesa", 3, tipoEstoqueCategoria.MOVEL));

            List<Item> itensEletronico = sistemaDeposito.buscarPorCategoria(tipoEstoqueCategoria.ELETRONICO);
            List<Item> itensMovel = sistemaDeposito.buscarPorCategoria(tipoEstoqueCategoria.MOVEL);

            assertEquals(1, itensEletronico.size());
            assertEquals(1, itensMovel.size());
        }

    }


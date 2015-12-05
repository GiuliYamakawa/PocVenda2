package br.univel.teste;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import br.univel.dao.ClienteDAO;
import br.univel.enums.Estados;
import br.univel.enums.Genero;
import br.univel.model.Cliente;

public class TestesCliente {
	
	ClienteDAO cdao = new ClienteDAO();
	
	int clienteTeste = 473;

	@Test
	public void testeInsertClientes(){
		
		boolean deuCerto = false;
		
		for (int i = 1; i < 51; i++){ // inserir 50 registros
			Cliente c = new Cliente();
			c.setCidade("Cidade "+i);
			c.setEmail("cliente@cliente"+i+".com");
			c.setEndereco("Rua "+i);
			c.setEstado(Estados.PR);
			c.setGenero(Genero.FEMININO);
			c.setNome("Cliente XYZ "+i);
			c.setTelefone(String.valueOf(i*1234));
			try{ 
				cdao.inserir(c); // se inseriu deucerto verdadeiro
				deuCerto = true;
			}catch(Exception e){
				e.printStackTrace();
				deuCerto = false; //se nao false
			}
		}
		assertEquals(true, deuCerto); // verifica os resultados se deu certo
	}
	
	@Test
	public void testeGetClienteNotNull(){ // verifica se o cliente informado existe no banco
		Cliente teste = null;
		teste = cdao.buscar(clienteTeste);
		assertEquals(true, teste != null);
	}
	
	@Test
	public void testeUpdateCliente(){ // edita o cliente informado
		Cliente teste = cdao.buscar(clienteTeste);
		int genero = 0;
		if(teste.getGenero().equals(Genero.FEMININO))
			genero = 1;
		if(teste.getGenero().equals(Genero.MASCULINO))
			genero = 2;
		
		if(genero == 1){
			teste.setGenero(Genero.MASCULINO);
		if(genero == 2)
			teste.setGenero(Genero.FEMININO);
		}
		
		cdao.atualizar(teste); // atualiza
		Cliente atualizado = cdao.buscar(teste.getId());
		assertEquals(teste.getGenero(), atualizado.getGenero());
	}
	
	@Test
	public void testeDeleteCliente(){ // remove 50 registro menos o cliente informado
		List<Cliente> teste = cdao.getClientes();
		int deletado = 0;
		for (Cliente cliente : teste) {
			if (cliente.getId()==clienteTeste)continue;
			if (deletado >= 50)break;
			cdao.deletar(cliente.getId());
			deletado++;
		}
		assertEquals(deletado, 50);
	}
	
}



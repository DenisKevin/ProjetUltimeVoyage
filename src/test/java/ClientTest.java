import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import fr.adaming.model.Civilite;
import fr.adaming.model.Client;
import fr.adaming.service.IClientService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/application-context.xml" })
public class ClientTest {

	@Autowired
	private IClientService clientService;

	public void setClientService(IClientService clientService) {
		this.clientService = clientService;
	}

	@Ignore
	@Test
	@Rollback
	@Transactional
	public void testAddClient() {
		Client client = new Client("a", "a", Civilite.Monsieur, "a", "a", "a");

		client = clientService.addClient(client);
		System.out.println("===== testAddClient : " + client);
		assertEquals(client.getIdentifiant(), "a");
	}

	@Ignore
	@Test
	@Rollback
	@Transactional
	public void testGetAllClients() {
		int tailleListeAttendu = 1;

		List<Client> liste = clientService.getAllClients();

		System.out.println("===== testGetAllClients : " + liste);
		assertEquals(liste.size(), tailleListeAttendu);

	}

	@Ignore
	@Test
	@Rollback
	@Transactional
	public void testGetClient() {
		
		Client clientGet = clientService.getClientById(4);

		assertEquals("1" , clientGet.getIdentifiant());

	}
	
	@Ignore
	@Test
	@Rollback
	@Transactional
	public void testUpdateClient() {
		Client clientGet = clientService.getClientById(4);

		clientGet.setIdentifiant("test");
		
		clientService.updateClient(clientGet);
		
		assertEquals("test" , clientGet.getIdentifiant());
	}
	
	@Ignore
	@Test
	@Rollback
	@Transactional
	public void testDeleteClient() {
		int sizeList = clientService.getAllClients().size();
		
		clientService.deleteClient(4);
		
		assertEquals(sizeList-1, clientService.getAllClients().size());
	}
}

package service;

import model.Carro;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CarroServiceImplTest {

    CarroService carroService;
    @Before
    public void setup() {
        carroService = new CarroServiceImpl();
    }

    /* Enquanto o carro estiver desligado deve ser capaz de:
     *  - Ligar
     *  - Mostrar estado atual
    * */

    @Test
    public void deveIniciarDesligado() {

        // Dado
        Carro corsa =
                new Carro("prata", "Chevrolet", "Corsa", 1990, 100);

        // Quando
        // instanciar um carro, deve verificar se esse está desligado

        // Então
        assertFalse(corsa.isLigado());

    }

    @Test
    public void deveLigarCorretamente() {

        // Dado
        Carro carro =
                new Carro("vermelho", "marca", "modelo", 1990, 100);

        // Quando
        carroService.ligar(carro);

        // Então
        assertTrue(carro.isLigado());
    }


    @Test
    public void deveMostrarSeuEstadoAtual() {

        // Dado
        Carro carro =
                new Carro("vermelho", "Fiat", "Uno", 2013, 100);

        // Quando
        // instanciar um carro deve exibir seu estado atual;

        // Então
        assertEquals(carroService.estadoAtual(carro), carro.toString());
    }

   /* Enquanto o carro estiver ligado deve ser capaz de:
     * - Desligar
     * - Acelerar
     * - Frear
     * - Motrar estado atual
    */

    @Test
    public void deveDesligarCorretamente() {

        //Dado - Given
        Carro fordKa = new Carro("Preto", "Fork", "Ka", 2023, 100);
        carroService.ligar(fordKa);

        // Quando - When
        carroService.desligar(fordKa);

        //Então - Then
        assertFalse(fordKa.isLigado());
    }

    @Test
    public void deveAcelerarCorretamente() {

        // Given
        Carro carroTeste01 = new Carro("Azul", "Fiat", "Uno", 2015, 150);
        carroService.ligar(carroTeste01);

        // When
        carroService.acelerar(carroTeste01, 10);

        // Then
        // Assertivas
        // O Junit busca por Asserts no método para validar que o teste passou
        assertTrue(carroTeste01.getVelocidadeAtual() == 10);
    }

    @Test
    public void deveFrearCorretamente() {

        //Dado - Given
        Carro fordKa = new Carro("Preto", "Fork", "Ka", 2023, 100);
        carroService.ligar(fordKa);
        carroService.acelerar(fordKa, 100);

        //Quando - When
        carroService.frear(fordKa, 50);

        //Então - Then
        assertTrue("A frenagem não está funcionando Corretamente!", fordKa.getVelocidadeAtual() == 50);
    }

    /* Regras:
     *
     * - Só podemos realizar as ações como acelerar e frear com o carro ligado
     * - Só podemos desligar o carro quando ele parar totalmente (velocidadeAtual = 0)
     * - Não existe velocidade negativa
     * - O carro não pode passar de sua velocidade máxima
     *
    * */

    @Test
    public void deveAcelerarSomenteComCarroLigado() {
        // Dado - Given
        Carro nissan = new Carro("Prata", "Nissan", "Kicks", 2023, 200);
        carroService.ligar(nissan);

        // Quando - When
        carroService.acelerar(nissan, 100);

        //Então - Then
        assertTrue("Não é possível acelerar com o carro desligado!", nissan.isLigado());

    }

    @Test
    public void deveFrearSomenteComCarroLigado() {
        // Dado - Given
        Carro nissan = new Carro("Prata", "Nissan", "Kicks", 2023, 200);
        carroService.ligar(nissan);
        carroService.acelerar(nissan, 100);

        // Quando - When
        carroService.frear(nissan, 100);

        //Então - Then
        assertTrue("Não é possível frear com o carro desligado!", nissan.isLigado());

    }

    @Test
    public void deveDesligarSomenteSeVelocidadeIgualZero() {

        // Dado - Given
        Carro nissan = new Carro("Prata", "Nissan", "Kicks", 2023, 200);
        carroService.ligar(nissan);
        carroService.acelerar(nissan, 100);
        carroService.frear(nissan, 100);

        // Quando - When
        carroService.desligar(nissan);

        //Então - Then
        assertEquals("Não é possível desligar o carro com a Velocidade Atual maior que Zero", 0, nissan.getVelocidadeAtual());
    }
    @Test
    public void deveAcelerarAteVelocidadeMaximaPermitida() {
        // Dado - Given
        Carro nissan = new Carro("Prata", "Nissan", "Kicks", 2023, 200);
        carroService.ligar(nissan);


        // Quando - When
        carroService.acelerar(nissan, 200);
        carroService.acelerar(nissan, 200);

        //Então - Then
        assertTrue("Não é possível acelerar com o carro desligado!", nissan.isLigado());
        assertTrue("Velocidade não pode ultrapassar a velocidade Máxima permitida!", nissan.getVelocidadeAtual() <= nissan.getVelocidadeMaxima());
    }


    @Test
    public void velocidadeNaoDeveSerMenorQueZero() {
        // O Junit busca por Assets no método para validar que o teste passou

        // Dado:
        Carro celtinha = new Carro("Prata", "Chevrolet", "Celta", 2001, 50);
        carroService.ligar(celtinha); // velocidade zero

        // Quando:
        carroService.frear(celtinha, 10);

        // Então:
        assertTrue(celtinha.isLigado());
        assertEquals(0, celtinha.getVelocidadeAtual());
    }

    @Test
    public void deveLigarCorretamenteEAcelerarCorretamente() {
        // Dado:
        Carro celtinha = new Carro("Prata", "Chevrolet", "Celta", 2001, 50);

        // Quando:
        carroService.ligar(celtinha);
        carroService.acelerar(celtinha, 10);

        // Então:
        assertTrue(celtinha.isLigado());
        assertEquals(10, celtinha.getVelocidadeAtual());
    }

}
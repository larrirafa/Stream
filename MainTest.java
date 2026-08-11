import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    void deveConterApenasNomesFemininosNaListaFiltrada() {
        List<String> entrada = List.of(
                "Maria-Feminino",
                "João-Masculino",
                "Ana-Feminino",
                "Pedro-Masculino",
                "Julia-Feminino"
        );

        ArrayList<String> resultado = Main.filtrarMulheres(entrada);

        // A lista filtrada deve conter exatamente os nomes femininos esperados
        assertEquals(3, resultado.size());
        assertTrue(resultado.contains("Maria-Feminino"));
        assertTrue(resultado.contains("Ana-Feminino"));
        assertTrue(resultado.contains("Julia-Feminino"));

        // Nenhum nome masculino deve aparecer na lista de mulheres
        assertFalse(resultado.contains("João-Masculino"));
        assertFalse(resultado.contains("Pedro-Masculino"));
    }

    @Test
    void todosOsItensFiltradosDevemTerSexoComecandoComF() {
        List<String> entrada = List.of(
                "Carla-Feminino",
                "Bruno-Masculino",
                "Fernanda-F",
                "Lucas-Masculino"
        );

        ArrayList<String> resultado = Main.filtrarMulheres(entrada);

        assertFalse(resultado.isEmpty(), "A lista de mulheres não deveria estar vazia");
        for (String item : resultado) {
            String[] partes = item.split("-");
            assertTrue(partes.length > 1, "Item mal formatado encontrado: " + item);
            assertTrue(
                    partes[1].trim().toLowerCase().startsWith("f"),
                    "Item na lista de mulheres não tem sexo feminino: " + item
            );
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"Feminino", "FEMININO", "feminino", "F", "f", "fem"})
    void deveAceitarVariacoesDeMaiusculoMinusculoParaFeminino(String sexo) {
        List<String> entrada = List.of("Beatriz-" + sexo);

        ArrayList<String> resultado = Main.filtrarMulheres(entrada);

        assertEquals(1, resultado.size());
        assertTrue(resultado.get(0).startsWith("Beatriz"));
    }

    @Test
    void naoDeveIncluirMasculinoNaListaDeMulheres() {
        List<String> entrada = List.of("João-Masculino", "Pedro-M");

        ArrayList<String> resultado = Main.filtrarMulheres(entrada);

        assertTrue(resultado.isEmpty(), "Nomes masculinos não deveriam entrar na lista de mulheres");
    }

    @Test
    void deveIgnorarEntradasMalFormatadasSemSeparador() {
        List<String> entrada = List.of("SoNome", "Maria-Feminino");

        ArrayList<String> resultado = Main.filtrarMulheres(entrada);

        // "SoNome" não tem "-", então não deve gerar erro nem entrar na lista
        assertEquals(1, resultado.size());
        assertEquals("Maria-Feminino", resultado.get(0));
    }

    @Test
    void listaVaziaDeveResultarEmListaFiltradaVazia() {
        ArrayList<String> resultado = Main.filtrarMulheres(List.of());
        assertTrue(resultado.isEmpty());
    }
}

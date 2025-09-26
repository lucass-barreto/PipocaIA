package br.com.pipocaIA.enums;

public enum Categoria {
    ACAO("Ação"),
    AVENTURA("Aventura"),
    COMEDIA("Comédia"),
    DRAMA("Drama"),
    FICCAO("Ficção Científica"),
    TERROR("Terror"),
    SUSPENSE("Suspense"),
    ROMANCE("Romance"),
    ANIMACAO("Animação"),
    DOCUMENTARIO("Documentário"),
    MUSICAL("Musical"),
    FANTASIA("Fantasia");

    private final String nomeFormatado;

    Categoria(String nomeFormatado) {
        this.nomeFormatado = nomeFormatado;
    }

    public String getNomeFormatado() {
        return nomeFormatado;
    }

}

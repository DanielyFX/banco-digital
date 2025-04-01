package org.example.adapter.exception.handler.response;

public class ApiValidacaoErroResponse implements ApiSubErroResponse{
    private final String objeto;
    private final String mensagem;
    private String campo;
    private Object valorRejeitado;

    ApiValidacaoErroResponse(String objeto, String mensagem){
        this.objeto = objeto;
        this.mensagem = mensagem;
    }

    ApiValidacaoErroResponse(String objeto, String campo, Object valorRejeitado, String mensagem){
        this.objeto = objeto;
        this.campo = campo;
        this.valorRejeitado = valorRejeitado;
        this.mensagem = mensagem;
    }

    public String getObjeto() {
        return objeto;
    }

    public String getCampo(){
        return campo;
    }

    public Object getValorRejeitado(){
        return valorRejeitado;
    }

    public String getMensagem(){
        return mensagem;
    }
}

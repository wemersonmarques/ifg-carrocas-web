package br.edu.ifg.carrocasweb.dto;

import br.edu.ifg.carrocasweb.model.anuncio.Anuncio;
import br.edu.ifg.carrocasweb.model.veiculo.Veiculo;

import java.util.Date;

public class AnuncioDTO {
    private String tituloAnuncio;
    private Date dataCadastroAnuncio;
    private Long idUsuarioAnuncio;
    private boolean ativoAnuncio;
    private String telefoneAnuncio;
    private Long idMarcaVeiculo;
    private String modeloVeiculo;
    private String motorizacaoVeiculo;
    private long quilometragemVeiculo;

    public Anuncio getAnuncio() {
        return new Anuncio(tituloAnuncio, true, telefoneAnuncio);
    }

    public Veiculo getVeiculo() {
        return new Veiculo(modeloVeiculo, quilometragemVeiculo, motorizacaoVeiculo);
    }

    public String getTituloAnuncio() {
        return tituloAnuncio;
    }

    public void setTituloAnuncio(String tituloAnuncio) {
        this.tituloAnuncio = tituloAnuncio;
    }

    public Date getDataCadastroAnuncio() {
        return dataCadastroAnuncio;
    }

    public void setDataCadastroAnuncio(Date dataCadastroAnuncio) {
        this.dataCadastroAnuncio = dataCadastroAnuncio;
    }

    public Long getIdUsuarioAnuncio() {
        return idUsuarioAnuncio;
    }

    public void setIdUsuarioAnuncio(Long idUsuarioAnuncio) {
        this.idUsuarioAnuncio = idUsuarioAnuncio;
    }

    public boolean isAtivoAnuncio() {
        return ativoAnuncio;
    }

    public void setAtivoAnuncio(boolean ativoAnuncio) {
        this.ativoAnuncio = ativoAnuncio;
    }

    public String getTelefoneAnuncio() {
        return telefoneAnuncio;
    }

    public void setTelefoneAnuncio(String telefoneAnuncio) {
        this.telefoneAnuncio = telefoneAnuncio;
    }

    public Long getIdMarcaVeiculo() {
        return idMarcaVeiculo;
    }

    public void setIdMarcaVeiculo(Long idMarcaVeiculo) {
        this.idMarcaVeiculo = idMarcaVeiculo;
    }

    public String getModeloVeiculo() {
        return modeloVeiculo;
    }

    public void setModeloVeiculo(String modeloVeiculo) {
        this.modeloVeiculo = modeloVeiculo;
    }

    public String getMotorizacaoVeiculo() {
        return motorizacaoVeiculo;
    }

    public void setMotorizacaoVeiculo(String motorizacaoVeiculo) {
        this.motorizacaoVeiculo = motorizacaoVeiculo;
    }

    public long getQuilometragemVeiculo() {
        return quilometragemVeiculo;
    }

    public void setQuilometragemVeiculo(long quilometragemVeiculo) {
        this.quilometragemVeiculo = quilometragemVeiculo;
    }
}

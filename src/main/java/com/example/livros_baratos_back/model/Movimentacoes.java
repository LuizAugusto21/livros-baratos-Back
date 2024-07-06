package com.example.livros_baratos_back.model;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Movimentacoes {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	
    @Enumerated(EnumType.ORDINAL)
    private TipoMovimentacao tipoMovimentacao;
	private StatusMovimentacao status;
	@ManyToOne
    @JoinColumn(name = "livro_id")
	private Livro itemMovimentado;
    private Date dataMovimentacaoDate;
    

	public Date getDataMovimentacaoDate() {
		return dataMovimentacaoDate;
	}
	
	public void setDataMovimentacaoDate(Date dataMovimentacaoDate) {
		this.dataMovimentacaoDate = dataMovimentacaoDate;
	}

	public StatusMovimentacao getStatus() {
		return status;
	}

	public void setStatus(StatusMovimentacao status) {
		this.status = status;
	}

	public Livro getItemMovimentado() {
		return itemMovimentado;
	}

	public void setItemMovimentado(Livro itemMovimentado) {
		this.itemMovimentado = itemMovimentado;
	}
	
}

enum TipoMovimentacao {
    COMPRA,
    VENDA
}

enum StatusMovimentacao {
    FINALIZADO,
    PROCESSANDO,
    CANCELADO
}

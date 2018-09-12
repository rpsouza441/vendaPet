//package br.com.lojapet.builder;
//
//import java.util.ArrayList;
//import java.util.Calendar;
//import java.util.List;
//
//import br.com.lojapet.model.DespesaAPagar;
//import br.com.lojapet.model.StatusContaAPagar;
//
//public class DespesaAPagarBuilder {
//
//	private DespesaAPagar despesa;
//	List<DespesaAPagar> despesas = new ArrayList<DespesaAPagar>();
//
//	public DespesaAPagarBuilder(DespesaAPagar despesa) {
//		this.despesa = despesa;
//	}
//
//	private void gerarDespesas() {
//
//		switch (despesa.getPeriodicidade()) {
//		case Unica:
//			despesas = geraDespesasComBaseNaPeriodicidade(1, despesa);
//			break;
//
//		case Semanal:
//			despesas = geraDespesasComBaseNaPeriodicidade(7, despesa);
//			break;
//
//		case Quinzenal:
//			despesas = geraDespesasComBaseNaPeriodicidade(15, despesa);
//			break;
//
//		case Mensal:
//			despesas = geraDespesasComBaseNaPeriodicidade(30, despesa);
//			break;
//
//		case Trimestral:
//			despesas = geraDespesasComBaseNaPeriodicidade(90, despesa);
//			break;
//
//		case Semestral:
//			despesas = geraDespesasComBaseNaPeriodicidade(180, despesa);
//			break;
//
//		case Anual:
//			despesas = geraDespesasComBaseNaPeriodicidade(365, despesa);
//			break;
//		}
//
//	}
//
//	public List<DespesaAPagar> constroi() {
//		gerarDespesas();
//		return despesas;
//	}
//
//	private List<DespesaAPagar> geraDespesasComBaseNaPeriodicidade(int j, DespesaAPagar despesaVar) {
//		List<DespesaAPagar> listaDespesa = new ArrayList<DespesaAPagar>();
//		if (j > 1) {
//			int quantidadeParcelas = despesaVar.getQuantidadeParcelas();
//			Calendar data = (Calendar) despesaVar.getDataVencimento().clone();
//			for (int i = 0; i < quantidadeParcelas; i++) {
//				DespesaAPagar despesaTemp = transfereDespesaParaObjetoTemporario(despesaVar);
//				despesaTemp.setNome(despesaTemp.getNome() + " (" + (i + 1) + "/" + quantidadeParcelas + ")");
//				data.add(Calendar.DATE, j);
//				despesaTemp.
//				setDataVencimento((Calendar) data.clone());
//				listaDespesa.add(despesaTemp);
//			}
//		} else {
//			listaDespesa.add(despesaVar);
//		}
//		return listaDespesa;
//	}
//
//	private DespesaAPagar transfereDespesaParaObjetoTemporario(DespesaAPagar despesaVar) {
//		DespesaAPagar despesaTemp = DespesaAPagar.builder().nome(despesaVar.getNome())
//				.carteira(despesaVar.getCarteira()).dataPagamento(despesaVar.getDataPagamento())
//				.dataVencimento(despesaVar.getDataVencimento()).statusContaAPagar(StatusContaAPagar.NAOPAGO).id(despesaVar.getId())
//				.periodicidade(despesaVar.getPeriodicidade()).valor(despesaVar.getValor()).build();
//		return despesaTemp;
//	}
//
//}

package mx.florinda.pagamento;

public class PagamentoConfirmadoEvent {

    public final Long pedidoId;

    public PagamentoConfirmadoEvent(Long pedidoId) {
        this.pedidoId = pedidoId;
    }

}

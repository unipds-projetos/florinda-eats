package mx.florinda.pagamento;

import io.quarkus.hibernate.reactive.panache.Panache;
import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

import java.util.List;

@Path("/pagamentos")
public class PagamentoResource {

  @Inject
  @Channel("pagamentosConfirmados")
  Emitter<PagamentoConfirmadoEvent> pagamentosConfirmadosEmitter;

  @GET
  public Uni<List<Pagamento>> lista() {
    return Pagamento.listAll();
  }

  @GET
  @Path("/{id}")
  public Uni<Pagamento> porId(Long id) {
    return Pagamento.findById(id);
  }

  @PUT
  @Path("/{id}")
  public  Uni<Pagamento> confirma(Long id) {
    return Panache.withTransaction(() ->
            Pagamento.<Pagamento>findById(id)
                    .onItem().ifNotNull().invoke(pagamento -> {
                      pagamento.status = StatusPagamento.CONFIRMADO;
                      pagamentosConfirmadosEmitter.send(new PagamentoConfirmadoEvent(pagamento.pedidoId));
                    }));
  }

}

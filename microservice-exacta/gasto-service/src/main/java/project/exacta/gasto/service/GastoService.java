package project.exacta.gasto.service;

import project.exacta.gasto.entity.Gasto;

import java.util.List;

public interface GastoService {

    public List<Gasto> listAllGasto();
    public Gasto getGasto(Long id);
    public Gasto createGasto(Gasto gasto);
    public Gasto updateGasto(Long id, Gasto gasto);
    public Gasto deleteGasto(Long id);
}

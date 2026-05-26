package test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import model.Producto;
import service.ProductoService;

public class ProductoServiceTest {

    private ProductoService productoService;

    @Before
    public void setUp() {
        productoService = new ProductoService();
    }

    @Test
    public void testAgregarProductoNombreVacio() {
        Producto p = new Producto(0, "", "Descripcion", 10.0, 5, 1);
        productoService.agregarProducto(p);
        // No debe insertar, debe mostrar error
    }

    @Test
    public void testAgregarProductoPrecioNegativo() {
        Producto p = new Producto(0, "Zelda", "Descripcion", -5.0, 5, 1);
        productoService.agregarProducto(p);
        // No debe insertar, debe mostrar error
    }

    @Test
    public void testAgregarProductoStockNegativo() {
        Producto p = new Producto(0, "Zelda", "Descripcion", 10.0, -1, 1);
        productoService.agregarProducto(p);
        // No debe insertar, debe mostrar error
    }

    @Test
    public void testProductoToString() {
        Producto p = new Producto(1, "Zelda", "Descripcion", 10.0, 5, 1);
        assertNotNull(p.toString());
        assertTrue(p.toString().contains("Zelda"));
    }

    @Test
    public void testProductoGetters() {
        Producto p = new Producto(1, "Zelda", "Descripcion", 59.99, 10, 2);
        assertEquals(1, p.getIdProducto());
        assertEquals("Zelda", p.getNombre());
        assertEquals(59.99, p.getPrecio(), 0.01);
        assertEquals(10, p.getStock());
    }
}
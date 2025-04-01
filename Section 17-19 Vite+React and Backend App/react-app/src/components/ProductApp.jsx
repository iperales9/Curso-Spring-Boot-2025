import { useEffect, useState } from "react"
import { create, findAll, remove, update } from "../services/ProductService";
import { ProductGrid } from "./ProductGrid";
import { ProductForm } from "./ProductForm";

export const ProductApp = ({ title }) => {

    const [products, setProducts] = useState([]);

    const [productSelected, setProductSelected] = useState({
        id: 0,
        name: '',
        description: '',
        price: ''
    })

    const getProducts = async () => {
        const result = await findAll();
        setProducts(result.data._embedded.products);
    }

    useEffect(() => {
        getProducts();
    }, []);

    const handlerAddProduct = async (product) => {

        if (product.id > 0) {
            const response = await update(product);
            setProducts(products.map(prod => {

                if (prod.id == response.data.id) {
                    return { ...response.data }
                }

                return prod;
            }));
        } else {
            const response = await create(product);
            setProducts([...products, { ...response.data }]);
        }

    }

    const handlerRemoveProduct = (id) => {
        remove(id);
        setProducts(products.filter(p => p.id != id));
    }

    const handlerProductSelected = (product) => {
        setProductSelected({ ...product });
    }

    return (
        <>
            <div className="container my-4">
                <h1>{title}</h1>
                <div className="row">
                    <div className="col">
                        <ProductForm handlerAdd={handlerAddProduct} productSelected={productSelected} />
                    </div>
                    <div className="col">
                        {
                            products.length > 0 ? <ProductGrid products={products} handlerRemove={handlerRemoveProduct} handlerProductSelected={handlerProductSelected} />
                                : <div className="alert alert-warning">No hay productos en el sistema!</div>
                        }

                    </div>
                </div>
            </div>
        </>
    )
}
import axios from "axios"



const initProducts = [
    {
        id: 1,
        name: 'Monitor Samsung 65',
        price: 500,
        description: 'El monitor es increible!'
    },
    {
        id: 2,
        name: 'Iphone 14',
        price: 800,
        description: 'El telefono es muy bueno'
    }
]

export const listProduct = () => {
    return initProducts;
}

const baseUrl = 'http://localhost:8080/products';

export const findAll = async () => {

    try {
        const response = await axios.get(baseUrl)
        return response;
    } catch (error) {
        console.log(error)
    }

    return null

}

export const create = ({ name, description, price }) => {

    try {
        const response = axios.post(baseUrl, {
            name,
            price,
            description
        })

        return response;

    } catch (error) {
        console.log(error)
    }
    return null
}

export const update = ({ id, name, description, price }) => {

    try {
        const response = axios.put(`${baseUrl}/${id}`, {
            name,
            price,
            description
        })

        return response;

    } catch (error) {
        console.log(error)
    }
    return null
}

export const remove = (async id => {
    try {
        await axios.delete(`${baseUrl}/${id}`)
    } catch (error) {
        console.log(error);
    }
})
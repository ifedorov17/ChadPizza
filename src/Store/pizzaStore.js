import {makeAutoObservable} from "mobx";

export default  class PizzaStore {
    constructor() {
        this._pizzaProps = [
         /*   { id : 1, name : "Маргарита", price : "600", pictureUrl : "https://lh3.googleusercontent.com/-F7-f2RyixFJ_0-MIGehlz7lp08CkWuy7Y64qDx8zcSrAyHA_uWVnJx1XOVAHg_qoFD7fW34aWScKlOz7tlHx8LeBxDoB64vaZ6LCKKMAPPnr8-QTpPpQVVK-xGPWFZomSVkVZXW"},
            { id : 2, name : "4 Сыра", price : "800", pictureUrl : "https://e2.edimdoma.ru/data/recipes/0010/4988/104988-ed4_wide.jpg?1628783796" },
            { id : 3, name : "Пеперони", price : "800", pictureUrl : "https://s1.eda.ru/StaticContent/Photos/120131085053/171027192707/p_O.jpg"},
            { id : 4, name : "От шефа", price : "900", pictureUrl : "https://irecommend.ru/sites/default/files/product-images/1502324/9GTaqO1eKqLg56gggJBv9A.jpg"},
            { id : 5, name : "От ", price : "90", pictureUrl : "https://irecommend.ru/sites/default/files/product-images/1502324/9GTaqO1eKqLg56gggJBv9A.jpg"},*/
        ]

        makeAutoObservable(this)
    }

    setPizzaProps(props) {
        this._pizzaProps = props
    }

    getPizzaProps() {
        return this._pizzaProps
    }

}


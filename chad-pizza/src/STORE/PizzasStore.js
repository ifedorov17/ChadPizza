import {makeAutoObservable} from "mobx";

export default  class PizzasStore {
    constructor() {
        this._pizzaProps = [
            {name : "Маргарита", price : "600", img : "https://lh3.googleusercontent.com/-F7-f2RyixFJ_0-MIGehlz7lp08CkWuy7Y64qDx8zcSrAyHA_uWVnJx1XOVAHg_qoFD7fW34aWScKlOz7tlHx8LeBxDoB64vaZ6LCKKMAPPnr8-QTpPpQVVK-xGPWFZomSVkVZXW"},
            {name : "4 Сыра", price : "800", img : "https://e2.edimdoma.ru/data/recipes/0010/4988/104988-ed4_wide.jpg?1628783796" },
            {name : "Пеперони", price : "800", img : "https://s1.eda.ru/StaticContent/Photos/120131085053/171027192707/p_O.jpg"},
            {name : "От шефа", price : "900", img : "https://irecommend.ru/sites/default/files/product-images/1502324/9GTaqO1eKqLg56gggJBv9A.jpg"},
        ]

        makeAutoObservable(this)
    }

    setPizzaProps(props) {
        this._pizzaProps = props
    }

    get pizzaProps() {
        return this._pizzaProps
    }

}
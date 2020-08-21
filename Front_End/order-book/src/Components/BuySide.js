import React from "react";
import { Table, Button } from "react-bootstrap";


const BuySideHeader = () => {
    return (
        <tr>
            <th>Cancel</th>
            <th>Symbol</th>
            <th>total</th>
            <th>quantity</th>
            <th>price</th>
        </tr>
    );
}

const BuySideRow = ({ order }) => {

    return (<tr>
        <td><Button>Cancel</Button></td>
        <td>{order.symbol}</td>
        <td>{order.price * order.ordersize}</td>
        <td>{order.ordersize}</td>
        <td>{order.price}</td>

    </tr>
    );
}

class BuySide extends React.Component {

    static defaultProps = {
        orders: [
            {
                "price": 1.00,
                "quantity": 100,
                "symbol": "AMZN"

            },
            {
                "price": 0.99,
                "quantity": 110,
                "symbol": "AMZN"

            },
            {
                "price": 0.98,
                "quantity": 120,
                "symbol": "AMZN"

            }
        ]
    }


    render() {
        // Here is a debug method to moniter incoming contact data
        console.log("Rending Order Book:")
        console.log(this.props.orders)


        return (
            <Table striped bordered hover>
                <thead>
                    <BuySideHeader />
                </thead>
                <tbody>
                    {this.props.orders.map((order, i) => {
                        return <BuySideRow order={order} key={i} />
                    })}

                </tbody>
            </Table>)
    }
}

export default BuySide
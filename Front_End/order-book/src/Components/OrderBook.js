import React, { Component } from 'react';

import BuySide from "./BuySide"
import SellSide from "./SellSide"
import { Container, Row, Col } from 'react-bootstrap'

class OrderBook extends Component {

    render() {
        return (
            <Container>
                <Row>
                    <Col sm={6}>
                        <BuySide />
                    </Col>
                    <Col sm={6}>
                        <SellSide />
                    </Col>
                </Row>
            </Container>
        )
    }
}

export default OrderBook
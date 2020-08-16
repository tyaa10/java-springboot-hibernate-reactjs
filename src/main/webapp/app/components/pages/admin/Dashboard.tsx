import React, { Component } from 'react'
import {Card, CardTitle, Col, Icon, Row} from "react-materialize"
import { NavLink } from 'react-router-dom'
import ResizeImage from 'react-resize-image'

class Dashboard extends Component {
    render () {
        return <Row>
            <Col
                s={12}
                m={4}
                l={4}
                xl={4}
            >
                <Card
                    actions={[
                        <NavLink
                            key={'/admin/categories'}
                            as={NavLink}
                            to={'/admin/categories'}
                        >
                            Go
                        </NavLink>
                    ]}
                    closeIcon={<Icon>close</Icon>}
                    header={
                        <ResizeImage
                            src="images/category-tree.jpg"
                            alt="Categories"
                            options={{ width: 300, height: 300, mode: 'stretch' }}
                        />
                    }
                    horizontal
                    revealIcon={<Icon>more_vert</Icon>}
                >
                    Product Categories
                </Card>
            </Col>
            <Col
                s={12}
                m={4}
                l={4}
                xl={4}
            >
                <Card
                    actions={[
                        <NavLink
                            key={'/admin/products'}
                            as={NavLink}
                            to={'/admin/products'}
                        >
                            Go
                        </NavLink>
                    ]}
                    closeIcon={<Icon>close</Icon>}
                    header={
                        <ResizeImage
                            src="images/goods.jpg"
                            alt="Goods"
                            options={{ width: 300, height: 300, mode: 'stretch' }}
                        />
                    }
                    horizontal
                    revealIcon={<Icon>more_vert</Icon>}
                >
                    Goods List
                </Card>
            </Col>
        </Row>
    }
}

export default Dashboard
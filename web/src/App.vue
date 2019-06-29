<template>
    <div id="app">
        <div id="toolbar">
            <Toolbar />
        </div>
        <div id="content">
            <Content />
        </div>
    </div>
</template>

<script>
    import Toolbar from './components/layout/Toolbar'
    import Content from './components/layout/Content'

    export default {
        name: "healthcheckdashboard",
        components: {
            Toolbar,
            Content
        }
    }
</script>

<style>
    div {
        font-family: "Open Sans";
    }
</style>
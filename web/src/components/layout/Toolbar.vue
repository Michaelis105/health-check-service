<template>
    <v-toolbar :clipped-let="$vuetify.breakpoint.lgAndUp" black dark app fixed>
        <t-toolbar-title style="width: 300px" class="ml-0 pl-3">
            <span id="dashboardTitle">Health Check</span>
        </t-toolbar-title>
        <v-spacer></v-spacer>
        <v-btn color="grey darken-1">
            <v-icon left>assessment</v-icon>
            Health Check
        </v-btn>
        <v-btn color="grey darken-1">
            <v-icon left>settings_applications</v-icon>
            Application Control
        </v-btn>
        <v-spacer></v-spacer>
        <v-btn icon>
            <v-icon>phone</v-icon>
        </v-btn>
        <v-btn icon>
            <v-icon>settings</v-icon>
        </v-btn>
    </v-toolbar>
</template>

<script>
    export default {
        name: "Toolbar"
    }
</script>

<style scoped>
    #dashboardTitle {
        font-weight: 600;
    }
</style>
<template>
    <v-card v-bind:color="this.cardColor">
        <v-card-title primary-title>
            <div>
                <div class="title mb-0">{{ service.name }}</div>
                <div v-bind:style="this.textColor" class="subheading"> {{ service.status }}</div>
                <div v-for="reason in service.reason">
                    <div class="body-2"> {{ reason }} </div>
                </div>
            </div>
        </v-card-title>
        <v-card-actions>
        </v-card-actions>
    </v-card>
</template>

<script>
    export default {
        name: "ServiceItem",
        props: ["service"],
        data() {
            return {
                cardColor : '',
                textColor : ''
            }
        },
        mounted() {
            switch (this.service.status) {
                case "Healthy":
                    this.cardColor = 'green darken-1';
                    this.textColor = 'white';
                    break;
                case "Unhealthy":
                    this.cardColor = 'yellow darken-1';
                    this.textColor = 'black';
                    break;
                case "Critical":
                    this.cardColor = 'red darken-1';
                    this.textColor = 'white';
                    break;
                default:
                    this.cardColor = 'grey lighten-1';
                    this.textColor = 'white';
            }
        }
    }
</script>

<style scoped>

</style>
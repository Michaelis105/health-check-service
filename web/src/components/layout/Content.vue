<template>
    <v-content>
        <v-container fluid grid-list-xl>
            <v-layout justify-start align-start justify-center wrap>
                <v-progress-circular v-if="loading" :size="50" color="primary" indeterminate></v-progress-circular>
                <v-flex v-for="service in svs" v-bind:ey="service.id" xs12 sm4 md3 lg3>
                    <ServiceItem v-bind:service="service"></ServiceItem>
                </v-flex>
            </v-layout>
        </v-container>
    </v-content>
</template>

<script>

    import axios from 'axios';
    import ServiceItem from '../ServiceItem';

    export default {
        name: "Content",
        components: {
            ServiceItem
        },
        data() {
            return {
                svs: [],
                loading: true
            }
        },
        methods: {
            getHealthStatuses: function() {
                axios.get("http://localhost:1111/api/healthCheckAll").then(response => {
                    this.healthResponse = response
                }).catch(error => {
                    this.healthErrorReason = error
                })
                return this.healthResponse;
            },

            healthCheckAll: function() {
                this.svs = new Array();
                this.getHealthStatuses();

                if (!this.healthResponse) {
                    this.loading = true;
                    return;
                }

                this.loading = false;

                var serviceStatusData = this.healthResponse.data;
                var serviceStatuses = serviceStatusData.healthStatuses;
                for (var i = 0; i < serviceStatuses.length; i++) {
                    var service = new Object();
                    service.name = serviceStatuses[i].serviceName;
                    service.status = serviceStatuses[i].healthStatus;
                    service.reason = serviceStatuses[i].reasons;
                    this.svs.push(service);
                }
            }
        },
        created() {
            this.healthCheckAll();
            setInterval(function() {
                this.healthCheckAll();
            }.bind(this), 10000)
        }
    }
</script>

<style scoped>

</style>
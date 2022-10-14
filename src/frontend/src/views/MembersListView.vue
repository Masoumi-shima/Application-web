<template>
  <div class="container">

    <div class="row my-5">
      <h1>Liste des membres</h1>
    </div>

    <div class="row my-5">
      <div class="col-6">
        <table class="table table-hover">
          <thead class="table-head">
            <th>Prénom</th>
            <th>Nom</th>
            <th>Date de naissance</th>
            <th>Adresse courriel</th>
            <th>Genre</th>
            <th>Action</th>
          </thead>
          <tbody class="table-group-divider">
            <tr v-for="member in members" :key="member.permitNumber">
              <td>{{member.firstName}}</td>
              <td>{{member.lastName}}</td>
              <td>{{member.birthDate}}</td>
              <td>{{member.email}}</td>
              <td>{{member.gender}}</td>
              <td>
                <router-link :to="'/membres/' + member.permitNumber">Voir</router-link>
              </td>
            </tr>
          </tbody>
        </table>
      </div>

      <div class="col-6">
        <MemberDetails v-if="this.$route.params.id != null" />
      </div>

    </div>
  </div>

</template>

<script>
import MemberService from '../services/MemberService'
import MemberDetails from '@/components/MemberDetails'

export default {
  name: 'MembersList',
  data() {
    return {
      members: []
    }
  },
  components: {
    MemberDetails
  },
  methods: {
    async getMembers() {
      await MemberService.getMembers()
          .then((response) => {
            this.members = response.data
          })
          .catch(e => {
            alert(e)
          })
    }
  },
  created() {
    this.getMembers()
  }
}
</script>

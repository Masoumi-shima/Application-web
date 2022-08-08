<template>
  <div class="details" v-if="currentMember">
    <h1>Membre</h1>
    <p>Prénom : {{currentMember.firstName}}</p>
    <p>Nom : {{currentMember.lastName}}</p>
    <p>Date de naissance : {{currentMember.birthDate}}</p>
    <p>Adresse Courriel : {{currentMember.email}}</p>
    <p>Genre : {{currentMember.gender}}</p>
    <p>A réussi l'examen: {{currentMember.passedExam}} </p>
  </div>

</template>

<script>

import MemberService from "@/services/MemberService";

export default {
  name: 'MemberDetails',
  data() {
    return {
      currentMember: null
    }
  },
  methods: {
    getMember(permitNumber) {
      MemberService.getAMember(permitNumber)
          .then((response) => {
            this.currentMember = response.data
          })
    }
  },
  created() {
    this.getMember(this.$route.params.id)
  }
}
</script>
<style>
.details {
    margin-right: 0;
    alignment: center;
  }

h1 {
    margin-right: 0;
    align-content: center;
  }

</style>
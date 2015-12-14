SELECT count(eleve.codeclasse) as effectif,count(absence.matricule) as nbreAbsence,count(retard.dureeretard) as dureeretard,sanction.dureesanction, eleve.nom, eleve.prenom, nomens,prenomens, datenaissance,lieunaissance,emailtuteur
,classe.anneeacademique,classe.codeoption,etablissement.nometab,etablissement.adresse,etablissement.logo,etablissement.telephone,etablissement.devise,etablissement.boite_postal,etablissement.site_web,etablissement.email,etablissement.region,etablissement.departement from eleve,enseignant,classe,optionclasse,annee,etablissement,absence,sanction,retard
where eleve.matricule=$P{matricule} and classe.codeclasse=$P{classe} and
enseignant.codeenseignant=classe.codeenseignant and
classe.codeoption=optionclasse.codeoption and
sanction.type='exclusion'
and eleve.matricule= retard.matricule and classe.codeclasse=eleve.codeclasse and
absence.justifie='non' and eleve.matricule=absence.matricule
and
classe.anneeacademique=annee.anneeacademique GROUP BY eleve.codeclasse,absence.matricule,retard.dureeretard;
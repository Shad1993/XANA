package com.example.model;

	public class Employe {
		private String No_employe;
		private String Nom;
		private String Prenom;
		private String NIC;
		private String DOB;
		private String Sexe;
		private String Adresse;
		private String Email;
		private String No_contact;
		private String Titre;
		private String Salaire;
		private String DateDembauche;
		private String Comission;
		private String No_dept;
		private String Nom_dept;



		public Employe() {
			
		}
		
		public Employe(String Nom,String Prenom,String NIC,String DOB,String Sexe,String Adresse,String Email,String No_contact,	
			String Titre,String Salaire,String DateDembauche, String Comission, String No_dept,String Nom_dept){
			 
			 

			 this.Nom = Nom;
			 this.Prenom = Prenom;
			 this.NIC = NIC;
			 this.DOB = DOB;
			 this.Sexe = Sexe;
			 this.Adresse = Adresse;
			 this.Email = Email;
			 this.No_contact = No_contact;
			 this.Titre = Titre;
			 this.Salaire =Salaire;
			 this.DateDembauche = DateDembauche;
			 this.Comission = Comission;
			 this.No_dept =No_dept;
			 this.Nom_dept =Nom_dept;

			
		}
		
		// set and get method à cause des variables privés
		
		public String getNo_employe() {
			return No_employe;
		}
		public  void setNo_employe(String No_employe) {
			 this.No_employe = No_employe;

		}
		
		
		public String getNom() {
			return Nom;
		}
		public  void setNom(String Nom) {
			 this.Nom = Nom;

		}
		
		
		public String getPrenom() {
			return Prenom;
		}
		public void setPrenom(String Prenom) {
			 this.Prenom = Prenom;
		}
		
		
		public String getNIC() {
			return NIC;
		}
		public void setNIC(String NIC) {
			this.NIC =NIC;
		}
		
		
		
		public String getDOB() {
			return DOB;
		}
		public void setDOB(String DOB) {
			 this.DOB = DOB;
		}
		
		
		
		public String getSexe() {
			return Sexe;
		}
		public void setSexe(String Sexe) {
			 this.Sexe = Sexe;
		}
		
		
		
		
		
		public String getAdresse() {
			return Adresse;
		}
		public void setAdresse(String Adresse) {
			 this.Adresse = Adresse;
		}
		
		
		
		public String getEmail() {
			return Email;
		}
		public void setEmail(String Email) {
			 this.Email = Email;
		}
		
		
		
		public String getNo_contact() {
			return No_contact;
		}
		public void setNo_contact(String No_contact) {
			 this.No_contact = No_contact;
		}
		
		
		
		public String getTitre() {
			return Titre;
		}
		public void setTitre(String Titre) {
			 this.Titre = Titre;
		}
		
		
		
		
		public String getSalaire() {
			return Salaire;
		}
		public void setSalaire(String Salaire) {
			 this.Salaire =Salaire;
		}
		
		
		
		
		public String getDateDembauche() {
			return DateDembauche;
		}
		public void setDateDembauche(String DateDembauche) {
			 this.DateDembauche = DateDembauche;
		}
		
		
		
		public String getComission() {
			return Comission;
		}
		public void setComission(String Comission) {
			 this.Comission = Comission;
		}
		
		public String getNo_dept() {
			return No_dept;
		}
		public void setNo_dept(String No_dept) {
			 this.No_dept =No_dept;
		}
		
		public String getNom_dept() {
			return Nom_dept;
		}
		public void setNom_dept(String Nom_dept) {
			 this.Nom_dept =Nom_dept;
		}
		
		
		
		@Override
		public String toString(){
			StringBuilder sb = new StringBuilder();
				sb.append("Employe[");
				sb.append("No Emp:"+this.No_employe);
				sb.append(",Nom:"+this.Nom);
				sb.append(",Prenom:"+this.Prenom);
				sb.append(",Adresse:"+this.Adresse);
				sb.append(",Sexe:"+this.Sexe);
				sb.append(",Prenom:"+this.Prenom);
				sb.append(",Salaire:"+this.Salaire);
				sb.append(",Commission:"+this.Comission);
				sb.append(",DOB:"+this.DOB);
				sb.append(",Date Embauche:"+this.DateDembauche);
				sb.append(",Titre:"+this.Titre);
				sb.append(",No contact:"+this.No_contact);
				sb.append(",Email:"+this.Email);
				sb.append(",No dep:"+this.No_dept);
				sb.append("]");
			return sb.toString();
		}
		
		
		
		
	

	}
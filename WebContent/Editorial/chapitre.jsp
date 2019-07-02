<%@page import="com.absoluteknowledge.model.*"%>
<%@page import="com.absoluteknowledge.service.*"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>

<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, user-scalable=no" />
<link rel="stylesheet" href="assets/css/main.css" />
</head>
<body class="is-preload">

	<!-- Wrapper -->
	<div id="wrapper">

		<!-- Main -->
		<div id="main">
			<div class="inner">

				<!-- Header -->
				<header id="header">
					<a href="index.html" class="logo"><strong>Absolute
							Knowledge</strong> by M2i</a>
					<ul class="icons">
						<li class="active"><a href="index.html"><span>Accueil</span></a></li>
						<li><a href="cours.html"><span>Cours</span></a></li>
						<li><a href="quizz.jsp"><span>Quizz</span></a></li>
					</ul>
				</header>

				<%
					ServiceCours sc = new ServiceCours();
					Cours cours = new Cours();
					cours = sc.getCoursById(1);

					// Liste de chapitres
					List<Chapitre> lstChapitres = cours.getChapitres();

					// Récupérer le chapitre sélectionné par l'utilisateur
					int id_chapitre = Integer.parseInt(request.getParameter("id_chapitre"));
					Chapitre chapitre = null;
					for (Chapitre chap : lstChapitres) {
						if (chap.getIndexee() == id_chapitre) {
							chapitre = chap;
							break;
						}
					}

					// Liste de parties du chapitre
					List<Partie> lstParties = chapitre.getParties();
				%>

				<!-- Content -->
				<section>
					<header class="main">
						<h1>

							<%
								/* Nom du chapitre */
								String titreChapitre = chapitre.getTitre();
								out.println(titreChapitre);
							%>

						</h1>
					</header>


					<%
						// Pour chaque partie du chapitre...
						for (Partie p : lstParties) {
							out.println("<h2 id=\"Partie" + p.getIndexee() + "\">" + p.getTitre() + "</h2>");

							// Liste de tous les éléments de la partie (paragraphes, images, code)
							Map<Integer, Object> ListeGlobale = new HashMap<Integer, Object>();

							/*
								Listes de paragraphes, images et codes du chapitre (pour chaque partie).
								On ne s'attend à aucun doublon parmi les indexee de chaque objet (image, paragraphe ou code).
							*/
							List<Image> lstImg = p.getImages();
							List<Paragraphe> lstParag = p.getParagraphes();
							List<Code> lstCode = p.getCodes();

							
							
							
							
							/*
								REPRENDRE ICI :
								TOUS LES OBJETS DE LA CLASSE PARAGRAPHE NE S'AFFICHENT PAS DANS LA CONSOLE
								(doublons d'indexee avec un autre élément de la partie ?)
							*/

							
							
							
							
							// Liste des paragraphes
							System.out.println("Liste des paragraphes : " + lstParag);

							for (Paragraphe parag : lstParag) {
								ListeGlobale.put(parag.getIndexee(), parag);
							}

							for (Image image : lstImg) {
								ListeGlobale.put(image.getIndexee(), image);
							}

							for (Code code : lstCode) {
								ListeGlobale.put(code.getIndexee(), code);
							}

							System.out.println("ListeGlobale : " + ListeGlobale);

							for (int i = 0; i < ListeGlobale.size(); i++) {

								System.out.println("Oobjet de la classe : " + ListeGlobale.get(i));

								switch (ListeGlobale.get(i).getClass().getSimpleName()) {
								case "Image":
									String titre = ((Image) ListeGlobale.get(i)).getTitre();
									out.println("<span class=\"image main\"><img src=\"images/pic11.jpg\" alt=\"" + titre
											+ "\" /></span>");
									break;
								case "Code":
									String contenu = ((Code) ListeGlobale.get(i)).getContenu();

									out.println("<pre class=\"default prettyprint prettyprinted\" style=\"\">" + "<code>"
											 + contenu + "</code>" + "</pre>");
									break;
								case "Paragraphe":
									String texte = ((Paragraphe) ListeGlobale.get(i)).getContenu();

									out.println("<p>" + texte + "</p>");
									break;
								default:
									System.out.println("Classe inconnue");
									out.println("<p>INCONNU</p>");
								}
							}

							out.println("<hr class=\"major\" />");
						}
					%>




				</section>

			</div>

			<footer id="footer">
				<p class="copyright">
					&copy; Copyright MaïManJul. Demo Images: <a
						href="https://unsplash.com">Unsplash</a>. Design: <a
						href="https://html5up.net">HTML5 UP</a>.
				</p>
			</footer>

		</div>

		<!-- Sidebar -->
		<div id="sidebar">
			<div class="inner">

				<!-- Search -->
				<section id="search" class="alt">
					<form method="post" action="#">
						<input type="text" name="query" id="query" placeholder="Search" />
					</form>
				</section>

				<!-- Menu -->
				<nav id="menu">
					<header class="major">
						<h2>Menu</h2>
					</header>

					<!-- Table des matières (avec liens clickables) -->
					<ul>
						<%
							for (Chapitre ch : cours.getChapitres()) {
								out.println("<li><span class=\"opener\">" + ch.getTitre() + "</span>");
								out.println("<ul>");
								for (Partie p : ch.getParties()) {
									out.println("<li><a href=\"?id_chapitre=" + cours.getChapitres().indexOf(ch) + "#Partie" + p.getIndexee()
											+ "\">" + p.getTitre() + "</a></li>");
								}
								out.println("</ul></li>");
							}
						%>
					</ul>

				</nav>


			</div>
		</div>
	</div>


	<!-- Scripts -->
	<script src="assets/js/jquery.min.js"></script>
	<script src="assets/js/browser.min.js"></script>
	<script src="assets/js/breakpoints.min.js"></script>
	<script src="assets/js/util.js"></script>
	<script src="assets/js/main.js"></script>

</body>
</html>
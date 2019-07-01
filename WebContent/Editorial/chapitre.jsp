<%@page import="com.absoluteknowledge.model.*"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
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
					List<Partie> ListePartChap1 = new ArrayList<Partie>();
					List<Partie> ListePartChap2 = new ArrayList<Partie>();
					List<Partie> ListePartChap3 = new ArrayList<Partie>();
					List<Partie> ListePartChap4 = new ArrayList<Partie>();
					Partie P1 = new Partie();
					P1.setTitre("Partie 1 du Chapitre 1");
					Partie P2 = new Partie();
					P2.setTitre("Partie 2 du Chapitre 1");
					Partie P3 = new Partie();
					P3.setTitre("Partie 1 du Chapitre 2");
					Partie P4 = new Partie();
					P4.setTitre("Partie 2 du Chapitre 2");
					Partie P5 = new Partie();
					P5.setTitre("Partie 3 du Chapitre 2");
					Partie P6 = new Partie();
					P6.setTitre("Partie 1 du Chapitre 3");
					Partie P7 = new Partie();
					P7.setTitre("Partie 1 du Chapitre 4");
					Partie P8 = new Partie();
					P8.setTitre("Partie 2 du Chapitre 4");
					ListePartChap1.add(P1);
					ListePartChap1.add(P2);
					ListePartChap2.add(P3);
					ListePartChap2.add(P4);
					ListePartChap2.add(P5);
					ListePartChap3.add(P6);
					ListePartChap4.add(P7);
					ListePartChap4.add(P8);

					Chapitre Chap1 = new Chapitre();
					Chapitre Chap2 = new Chapitre();
					Chapitre Chap3 = new Chapitre();
					Chapitre Chap4 = new Chapitre();

					Chap1.setParties(ListePartChap1);
					Chap1.setTitre("Chapitre 1");

					Chap2.setParties(ListePartChap2);
					Chap2.setTitre("Second chapitre");

					Chap3.setParties(ListePartChap3);
					Chap3.setTitre("3ème chapitre");

					Chap4.setParties(ListePartChap4);
					Chap4.setTitre("4ème chapitre");

					Cours C1 = new Cours();
					List<Chapitre> ListeChap = new ArrayList<Chapitre>();
					ListeChap.add(Chap1);
					ListeChap.add(Chap2);
					ListeChap.add(Chap3);
					ListeChap.add(Chap4);

					C1.setChapitres(ListeChap);
					C1.setTitre("Mon cours GIT");

					/*
						Listes de paragraphes, images et codes du chapitre 1 (partie 1).
						On ne s'attend à aucun doublon parmi les index de chaque objet (image, paragraphe ou code).
					*/
					List<Image> lstImg = new ArrayList<Image>();
					List<Paragraphe> lstParag = new ArrayList<Paragraphe>();
					List<Code> lstCode = new ArrayList<Code>();

					Image im1 = new Image();
					im1.setTitre("Image 1");
					im1.setIndex(1);

					Image im2 = new Image();
					im2.setTitre("Image 2");
					im2.setIndex(4);

					Code cod1 = new Code();
					cod1.setContenu("Hello World");
					cod1.setIndexee(2);

					Code cod2 = new Code();
					cod2.setContenu("Hello M2I");
					cod2.setIndexee(6);

					Paragraphe p1 = new Paragraphe();
					p1.setContenu("Explication de paragraphe P1");
					p1.setIndexee(3);

					Paragraphe p2 = new Paragraphe();
					p2.setContenu("Explication de paragraphe P2");
					p2.setIndexee(5);

					lstImg.add(im1);
					lstImg.add(im2);
					lstParag.add(p1);
					lstParag.add(p2);
					lstCode.add(cod1);
					lstCode.add(cod2);

					Map<Integer, Object> ListeGlobale = new HashMap<Integer, Object>();

					for (int i = 0; i < lstImg.size(); i++) {
						ListeGlobale.put(lstImg.get(i).getIndexee(), lstImg.get(i));
					}

					for (int i = 0; i < lstParag.size(); i++) {
						ListeGlobale.put(lstParag.get(i).getIndexee(), lstParag.get(i));
					}

					for (int i = 0; i < lstCode.size(); i++) {
						ListeGlobale.put(lstCode.get(i).getIndexee(), lstCode.get(i));
					}

					System.out.println("ListeGlobale : " + ListeGlobale);
				%>

				<!-- Content -->
				<section>
					<header class="main">
						<h1>

							<%
								/* Nom du chapitre */
								int id_chapitre = Integer.parseInt(request.getParameter("id_chapitre"));
								Chapitre chap = C1.getChapitres().get(id_chapitre);
								String titreChapitre = chap.getTitre();
								out.println(titreChapitre);
							%>

						</h1>
					</header>


					<%
						for (Partie p : chap.getParties()) {
							out.println("<h2 id=\"" + p.getTitre() + "\">" + p.getTitre() + "</h2>");

							for (int i = 1; i <= ListeGlobale.size(); i++) {
								
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
											+ "	<span class=\"pln\">&lt;" + contenu + "&gt;</span>" + "</code>" + "</pre>");
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
							for (Chapitre ch : C1.getChapitres()) {
								out.println("<li><span class=\"opener\">" + ch.getTitre() + "</span>");
								out.println("<ul>");
								for (Partie p : ch.getParties()) {
									out.println("<li><a href=\"?id_chapitre=" + C1.getChapitres().indexOf(ch) + "#" + p.getTitre()
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
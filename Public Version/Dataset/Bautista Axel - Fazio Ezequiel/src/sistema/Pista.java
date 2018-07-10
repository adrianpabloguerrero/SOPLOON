package sistema;

import java.util.Vector;

public class Pista extends ElementoMusical{

    int duracionSegs;
    String artista;
    String album;
    int id;
    int anio;
    String genero;
    private static int contador_id = 1;
    String comentario;

    public Pista(String nomb,int duraSegs,String artist, String alb, int ani, String gener) {
        super(nomb);
        id = contador_id;
        contador_id++;
        duracionSegs = duraSegs;
        artista = artist;
        album = alb;
        anio = ani;
        genero = gener;
    }

    public Pista(String nomb,int duraSegs,String artist, String alb, int ani, String gener, String coment){
        this(nomb, duraSegs, artist, alb, ani, gener);
        comentario = coment;
    }

    public String getResumen() {
        String resumen = "ID: " + id + ". Nombre: " + nombre +  ". Duraci�n: " + duracionSegs + " segs. Artista: " + artista + ". Album: " + album + ". A�o: " + anio + ". Genero: " + genero;
        if(comentario != null) resumen +=  ". Comentario: " +comentario + ".";
        return resumen;
    }

    public boolean equals(Object o1) {
        return nombre.equals(((Pista)o1).getNombre());
    }

    public int getCantidad() { return 1; }


    public Vector<Pista> buscador(Busqueda b) {
        Vector <Pista> resultado = new Vector<Pista>();
        if(b.busqueda(this)){
            resultado.add(this);
        }
        return resultado;
    }

    public Pista eliminarPista(Pista em) { return this; }

    public String getNombre(){
        return nombre;
    }
    public int getDuracion(){
        return duracionSegs;
    }
    public String getArtista(){
        return artista;
    }
    public String getAlbum(){
        return album;
    }
    public int getAnio(){
        return anio;
    }
    public String getGenero(){
        return genero;
    }
    public String getComentario(){
        return comentario;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public void setArtista(String artista) {
        this.artista = artista;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public void setDuracionSegs(int duracionSegs) {
        this.duracionSegs = duracionSegs;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }
}
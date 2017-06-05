package projeto;


import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.event.MouseInputListener;

import org.jxmapviewer.JXMapKit;
import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.OSMTileFactoryInfo;
import org.jxmapviewer.input.CenterMapListener;
import org.jxmapviewer.input.PanKeyListener;
import org.jxmapviewer.input.PanMouseInputListener;
import org.jxmapviewer.input.ZoomMouseWheelListenerCenter;
import org.jxmapviewer.viewer.DefaultTileFactory;
import org.jxmapviewer.viewer.GeoPosition;
import org.jxmapviewer.viewer.LocalResponseCache;
import org.jxmapviewer.viewer.TileFactoryInfo;
import org.jxmapviewer.viewer.WaypointPainter;



public class TelaPrincipal extends JFrame
{
	private JButton cadastrar_Funcionario,
					cadastrar_Caminhao,
					cadastrar_Caminhoneiro,
					cadastrar_GPS,
					cadastrar_computador,
					atualizar;
	// Metodo que coloca os componentes na GUI
	public TelaPrincipal()
	{
		super("Tela Principal");
		setLayout(null);
		
		cadastrar_Funcionario = new JButton("Cadastrar Funcionário");
		cadastrar_Funcionario.setBounds(30, 10, 200, 40);
		this.add(cadastrar_Funcionario);
		
		cadastrar_GPS = new JButton("Cadastrar GPS");
		cadastrar_GPS.setBounds(270, 10, 200, 40);
		add(cadastrar_GPS);
		
		cadastrar_Caminhao = new JButton("Cadastrar Caminhão");
		cadastrar_Caminhao.setBounds(510, 10, 200, 40);
		this.add(cadastrar_Caminhao);
		
		cadastrar_Caminhoneiro = new JButton("Cadastrar Caminhoneiro");
		cadastrar_Caminhoneiro.setBounds(750, 10, 200, 40);
		this.add(cadastrar_Caminhoneiro);
		
		cadastrar_computador = new JButton("Cadastrar computador");
		cadastrar_computador.setBounds(990, 10, 200, 40);
		this.add(cadastrar_computador);
		
		atualizar = new JButton("Atualizar");
		atualizar.setBounds(1230, 10, 100, 40);
		this.add(atualizar);
		mapa();
		
		ButtonHandler buttonHandler = new ButtonHandler();
		cadastrar_Funcionario.addActionListener(buttonHandler);
		cadastrar_GPS.addActionListener(buttonHandler);
		cadastrar_Caminhao.addActionListener(buttonHandler);
		cadastrar_Caminhoneiro.addActionListener(buttonHandler);
		cadastrar_computador.addActionListener(buttonHandler);
		atualizar.addActionListener(buttonHandler);
	}
	// Mapa da GUI
	public void mapa ()
	{
				// Create a TileFactoryInfo for OSM
		        TileFactoryInfo info = new OSMTileFactoryInfo();
		        DefaultTileFactory tileFactory = new DefaultTileFactory(info);
		        tileFactory.setThreadPoolSize(8);

		        // Setup local file cache
		        File cacheDir = new File(System.getProperty("user.home") + File.separator + ".jxmapviewer2");
		        LocalResponseCache.installResponseCache(info.getBaseURL(), cacheDir, false);
		        
		        // Setup JXMapViewer
		        JXMapViewer mapViewer = new JXMapViewer();
		        mapViewer.setTileFactory(tileFactory);
		        mapViewer.setBounds(10, 60, 1320, 620);
		        add(mapViewer);
		        
		        GeoPosition brasilia = new GeoPosition(-17,  47, 43, -49, 52,53);
		        GeoPosition riobranco = new GeoPosition(2,  49, 60, -60, 41, 38);
		        GeoPosition fortaleza = new GeoPosition(-4,  53, 30, -38, 36, 51);
		        GeoPosition portoalegre = new GeoPosition(-30, 2, 52, -51, 12, 1);
		        GeoPosition altaFloresta = new GeoPosition(-9,  52, 13, -56, 5, 1);
		        GeoPosition petrolina = new GeoPosition(-9, 23, 25, -40, 29, 54);
		        GeoPosition acre = new GeoPosition(-10, 58, 32, -67, 49, 93);
		        
		        // Set the focus
		        mapViewer.setZoom(15);
		        mapViewer.setAddressLocation(brasilia);

		        // Add interactions
		        MouseInputListener mia = new PanMouseInputListener(mapViewer);
		        mapViewer.addMouseListener(mia);
		        mapViewer.addMouseMotionListener(mia);
		        mapViewer.addMouseListener(new CenterMapListener(mapViewer));
		        mapViewer.addMouseWheelListener(new ZoomMouseWheelListenerCenter(mapViewer));
		        mapViewer.addKeyListener(new PanKeyListener(mapViewer));

		        // Create waypoints from the geo-positions
		        Set<SwingWaypoint> waypoints = new HashSet<SwingWaypoint>(Arrays.asList(
		                new SwingWaypoint("GPS 1", brasilia),
		                new SwingWaypoint("GPS 2", riobranco),
		                new SwingWaypoint("GPS 3", fortaleza),
		                new SwingWaypoint("GPS 4", portoalegre),
		                new SwingWaypoint("GPS 5", altaFloresta),
		                new SwingWaypoint("GPS 6", petrolina),
		                new SwingWaypoint("GPS 6", acre)
		                ));

		        // Set the overlay painter
		        WaypointPainter<SwingWaypoint> swingWaypointPainter = new SwingWaypointOverlayPainter();
		        swingWaypointPainter.setWaypoints(waypoints);
		        mapViewer.setOverlayPainter(swingWaypointPainter);

		        // Add the JButtons to the map viewer
		        for (SwingWaypoint w : waypoints) {
		            mapViewer.add(w.getButton());
		        } 
		        
	}
	// Classe para eventos de botões
	// Entrar em cada tela de cadastro
	public class ButtonHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{	
			if(e.getSource() == cadastrar_Caminhao)
			{
				JFrame quadroCadastro_Caminhao  = new CadastrarCaminhao();
				quadroCadastro_Caminhao.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				quadroCadastro_Caminhao.setMinimumSize(new Dimension(800, 325));
				quadroCadastro_Caminhao.setVisible(true);
				quadroCadastro_Caminhao.setLocationRelativeTo(null);
				setVisible(false);
			}
			else
			{
				if(e.getSource() == cadastrar_GPS)
				{
					JFrame quadroCadastro_GPS = new CadastrarGPS();
					quadroCadastro_GPS.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					quadroCadastro_GPS.setMinimumSize(new Dimension(800, 325));
					quadroCadastro_GPS.setVisible(true);
					quadroCadastro_GPS.setLocationRelativeTo(null);
					setVisible(false);
				}
				else
				{
					if(e.getSource() == cadastrar_Caminhoneiro)
					{
						JFrame quadroCadastro_Caminhoneiro = new CadastrarCaminhoneiro();
						quadroCadastro_Caminhoneiro.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
						quadroCadastro_Caminhoneiro.setMinimumSize(new Dimension(800, 325));
						quadroCadastro_Caminhoneiro.setVisible(true);
						quadroCadastro_Caminhoneiro.setLocationRelativeTo(null);
						setVisible(false);
					}
					else
					{
						if(e.getSource() == cadastrar_Funcionario)
						{
							JFrame quadorCadastro_Caminhoneiro = new CadastrarFuncionario();
							quadorCadastro_Caminhoneiro.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
							quadorCadastro_Caminhoneiro.setMinimumSize(new Dimension(800, 325));
							quadorCadastro_Caminhoneiro.setVisible(true);
							quadorCadastro_Caminhoneiro.setLocationRelativeTo(null);
							setVisible(false);
						}
						else
						{
							if(e.getSource() == cadastrar_computador)
							{
									JFrame quadrocadastro_computador = new CadastrarComputador();
									quadrocadastro_computador.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
									quadrocadastro_computador.setMinimumSize(new Dimension(800, 325));
									quadrocadastro_computador.setVisible(true);
									quadrocadastro_computador.setLocationRelativeTo(null);
									setVisible(false);
							}
							else
							{
								if(e.getSource() == atualizar)
								{
									// Create a TileFactoryInfo for OSM
							        TileFactoryInfo info = new OSMTileFactoryInfo();
							        DefaultTileFactory tileFactory = new DefaultTileFactory(info);
							        tileFactory.setThreadPoolSize(8);

							        // Setup local file cache
							        File cacheDir = new File(System.getProperty("user.home") + File.separator + ".jxmapviewer2");
							        LocalResponseCache.installResponseCache(info.getBaseURL(), cacheDir, false);
							        
							        // Setup JXMapViewer
							        JXMapViewer mapViewer = new JXMapViewer();
							        mapViewer.setTileFactory(tileFactory);
							        mapViewer.setBounds(10, 60, 1320, 620);
							        add(mapViewer);
							        
							        GeoPosition brasilia = new GeoPosition(-17,  47, 43, -49, 52,53);
							        GeoPosition riobranco = new GeoPosition(2,  49, 60, -60, 41, 38);
							        GeoPosition fortaleza = new GeoPosition(-4,  53, 30, -38, 36, 51);
							        GeoPosition portoalegre = new GeoPosition(-30, 2, 52, -51, 12, 1);
							        GeoPosition altaFloresta = new GeoPosition(-9,  52, 13, -56, 5, 1);
							        GeoPosition petrolina = new GeoPosition(-9, 23, 25, -40, 29, 54);
							        GeoPosition acre = new GeoPosition(-10, 58, 32, -67, 49, 93);
							        
							        // Set the focus
							        mapViewer.setZoom(15);
							        mapViewer.setAddressLocation(brasilia);

							        // Add interactions
							        MouseInputListener mia = new PanMouseInputListener(mapViewer);
							        mapViewer.addMouseListener(mia);
							        mapViewer.addMouseMotionListener(mia);
							        mapViewer.addMouseListener(new CenterMapListener(mapViewer));
							        mapViewer.addMouseWheelListener(new ZoomMouseWheelListenerCenter(mapViewer));
							        mapViewer.addKeyListener(new PanKeyListener(mapViewer));

							        // Create waypoints from the geo-positions
							        Set<SwingWaypoint> waypoints = new HashSet<SwingWaypoint>(Arrays.asList(
							                new SwingWaypoint("GPS 1", brasilia),
							                new SwingWaypoint("GPS 2", riobranco),
							                new SwingWaypoint("GPS 3", fortaleza),
							                new SwingWaypoint("GPS 4", portoalegre),
							                new SwingWaypoint("GPS 5", altaFloresta),
							                new SwingWaypoint("GPS 6", petrolina),
							                new SwingWaypoint("GPS 6", acre)
							                ));

							        // Set the overlay painter
							        WaypointPainter<SwingWaypoint> swingWaypointPainter = new SwingWaypointOverlayPainter();
							        swingWaypointPainter.setWaypoints(waypoints);
							        mapViewer.setOverlayPainter(swingWaypointPainter);

							        // Add the JButtons to the map viewer
							        for (SwingWaypoint w : waypoints) {
							            mapViewer.add(w.getButton());
							        }
								}
							}
						}
					}
				}
			}				
		}		
	}	
}

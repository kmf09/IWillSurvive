using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace Ecoregion.Handler
{
    class Constants
    {
        #region Contains a selection of global constants useful for readability in code within and pertaining to the Ecoregion.Handler namespace
        public const int ZERO = 0;

        public const int TRUE = 1;
        public const int FALSE = 0;
        
        public const int MAXLOCATIONNAMELENGTH = 40;
        public const int MINLOCATIONNAMELENGTH = 2;
        
        #region Eco Criteria
        #region Values
        public const int NUMCRITERIA = 26;
        public const int HIGHESTPOSSIBLESCORE = 5;
        public const int LOWESTPOSSIBLESCORE = 0;
        public const int CRITERIONFRIENDLY = 5;
        public const int CRITERIONHOSPITABLE = 4;
        public const int CRITERIONCHALLENGING = 3;
        public const int CRITERIONINHOSPITABLE = 2;
        public const int CRITERIONHOSTILE = 1;
        public const int CRITERIONEXCEPTIONAL = 0;
        #endregion
        #region Flora
        public const int FLORAEDIBILITY = 0;
        public const int FLORATOXICITY = 1;
        public const int FLORASHELTERSCORE = 2;
        public const int FLORAFIREWOODSCORE = 3;
        public const int FLORAHOSTILITY = 4;
        public const int FLORATRAVERSABILITY = 5;
        public const int FLORACANOPYCOVERAGE = 6;
        public const int FLORAHERBSCORE = 7;
        #endregion
        #region Fauna
        public const int FAUNAEDIBILITY = 8;
        public const int FAUNADISEASE = 9;
        public const int FAUNAAGGRESSION = 10;
        public const int FAUNAVENOM = 11;
        public const int FAUNAHUNTABILITY = 12;
        public const int FAUNAEXPLOITABILITY = 13;
        public const int FAUNADENSITY = 14;
        #endregion
        #region Terrain
        public const int TERRAINROCKINESS = 15;
        public const int TERRAINELEVATION = 16;
        public const int TERRAINTOPOGRAPHICS = 17;
        public const int TERRAINWATERSATURATION = 18;
        public const int TERRAINHAZARDS = 19;
        #endregion
        #region Climate
        public const int CLIMATELOWTEMPS = 20;
        public const int CLIMATEHIGHTEMPS = 21;
        public const int CLIMATEPRECIPITATION = 22;
        public const int CLIMATEHAZARDOUSWEATHER = 23;
        #endregion
        #region Water
        public const int WATERPREVALENCE = 24;
        public const int WATERPOTABILITY = 25;
        #endregion
        #endregion
        #region Biome Identifiers
        public const int TEST = 0;
        public const int ARCTIC = 1;
        public const int DESERT = 2;
        public const int FOREST = 3;
        public const int MOUNTAINS = 4;
        public const int PLAINS = 5;
        public const int SWAMP = 6;
        #endregion
        #endregion
    }
}
